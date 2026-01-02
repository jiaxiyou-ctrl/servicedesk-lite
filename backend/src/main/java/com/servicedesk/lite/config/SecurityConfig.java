package com.servicedesk.lite.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                // 1) 关闭 CSRF：我们是纯 API + JWT（无 session），一般不需要 CSRF
                .csrf(csrf -> csrf.disable())

                // 2) 无状态：不使用服务端 Session，每次靠 Bearer Token 鉴权
                .sessionManagement(sm -> sm.sessionCreationPolicy(STATELESS))

                // 3) 授权规则：从上到下匹配，先匹配到的规则先生效
                .authorizeHttpRequests(auth -> auth
                        // (a) 注册/登录接口放行
                        .requestMatchers("/auth/**", "/error").permitAll()

                        // (b) 管理员接口：必须 ADMIN
                        .requestMatchers("/admin/**").hasRole("ADMIN")

                        // (c) tickets：写/删仅 ADMIN（更严格的要写在前面）
                        .requestMatchers(HttpMethod.POST, "/tickets/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/tickets/**").hasRole("ADMIN")

                        // (d) tickets：读只要登录
                        .requestMatchers(HttpMethod.GET, "/tickets/**").authenticated()

                        // (e) 其它任何接口：只要登录
                        .anyRequest().authenticated()
                )

                // 4) 启用资源服务器 JWT：从 Authorization: Bearer <token> 里解析身份
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter()))
                )

                // 5) 关闭 basic / 表单 / logout：避免与你的 JWT 流程混在一起
                .httpBasic(b -> b.disable())
                .formLogin(f -> f.disable())
                .logout(l -> l.disable())

                .build();
    }

    /**
     * 把 JWT 的 claim: role=USER/ADMIN
     * 转成 Spring Security 权限：ROLE_USER / ROLE_ADMIN
     *
     * 这样你在上面写 hasRole("ADMIN") 才会生效。
     */
    @Bean
    public Converter<Jwt, ? extends AbstractAuthenticationToken> jwtAuthenticationConverter() {
        return jwt -> {
            String role = jwt.getClaimAsString("role");
            if (role == null || role.isBlank()) role = "USER";

            var authorities = List.of(new SimpleGrantedAuthority("ROLE_" + role));
            return new org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken(jwt, authorities);
        };
    }
}
