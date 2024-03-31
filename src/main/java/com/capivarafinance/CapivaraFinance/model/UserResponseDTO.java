package com.capivarafinance.CapivaraFinance.model;

public class UserResponseDTO {
    private String username;
    private String token;

    public UserResponseDTO(String username, String token) {
        this.setUsername(username);
        this.setToken(token);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
