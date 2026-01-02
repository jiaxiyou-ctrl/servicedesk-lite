package com.servicedesk.lite.auth;

import com.servicedesk.lite.user.User;
import com.servicedesk.lite.user.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User register(String email, String rawPassword) {
        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Email already registered");
        }

        String hash = encoder.encode(rawPassword); // （新概念：BCrypt）把密码变成不可逆的哈希

        User user = new User(email, hash, "USER", OffsetDateTime.now());
        return userRepository.save(user); // save（新概念）：把对象保存进数据库
    }
}

