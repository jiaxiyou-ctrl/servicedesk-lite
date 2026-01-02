package com.servicedesk.lite.auth;

import com.servicedesk.lite.user.User;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED) 
    public RegisterResponse register(@Valid @RequestBody RegisterRequest req) {
        User u = userService.register(req.getEmail(), req.getPassword());
        return new RegisterResponse(u.getId(), u.getEmail(), u.getRole(), u.getCreatedAt());
    }
}
