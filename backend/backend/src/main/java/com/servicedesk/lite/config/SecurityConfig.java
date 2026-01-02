package com.servicedesk.lite.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                // 纯 API 开发阶段通常先关 CSRF，减少“莫名其妙 403”干扰
                .csrf(csrf -> csrf.disable())

                // 不使用 Session（后面我们会用 JWT，每次请求带 token）
                .sessionManagement(sm -> sm.sessionCreationPolicy(STATELESS))

                // 授权规则：/auth/** 全放行，其它都要登录
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll()
                        .anyRequest().authenticated()
                )

                // 暂时保留 Basic（等你实现 JWT 后再替换/移除）
                .httpBasic(Customizer.withDefaults())

                .build();
    }
}
