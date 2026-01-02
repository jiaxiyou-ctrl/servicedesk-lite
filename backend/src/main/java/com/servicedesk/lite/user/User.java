package com.servicedesk.lite.user;

import jakarta.persistence.*;
import java.time.OffsetDateTime;

@Entity 
@Table(name = "users") 
public class User {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Column(nullable = false)
    private String role;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    protected User() { }

    public User(String email, String passwordHash, String role, OffsetDateTime createdAt) {
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;
        this.createdAt = createdAt;
    }

    public Long getId() { return id; }
    public String getEmail() { return email; }
    public String getPasswordHash() { return passwordHash; }
    public String getRole() { return role; }
    public OffsetDateTime getCreatedAt() { return createdAt; }
}
