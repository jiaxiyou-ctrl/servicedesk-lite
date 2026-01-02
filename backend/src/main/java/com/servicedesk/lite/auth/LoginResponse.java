package com.servicedesk.lite.auth;

public class LoginResponse {
    private final String tokenType = "Bearer";
    private final String accessToken;

    public LoginResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() { return tokenType; }
    public String getAccessToken() { return accessToken; }
}
