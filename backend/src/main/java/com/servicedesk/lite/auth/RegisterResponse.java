package com.servicedesk.lite.auth;

import java.time.OffsetDateTime;

public class RegisterResponse {
    private final Long id;
    private final String email;
    private final String role;
    private final OffsetDateTime createdAt;

    public RegisterResponse(Long id, String email, String role, OffsetDateTime createdAt) {
        this.id = id;
        this.email = email;
        this.role = role;
        this.createdAt = createdAt;
    }

    public Long getId() { return id; }
    public String getEmail() { return email; }
    public String getRole() { return role; }
    public OffsetDateTime getCreatedAt() { return createdAt; }
}

