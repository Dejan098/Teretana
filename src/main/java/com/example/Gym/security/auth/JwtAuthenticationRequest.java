package com.example.Gym.security.auth;

public class JwtAuthenticationRequest {

    private String email;
    private String password;

    public JwtAuthenticationRequest() {
        super();
    }

    public JwtAuthenticationRequest(String email, String password) {
        this.setUsername(email);
        this.setPassword(password);
    }

    public String getEmail() {
        return this.email;
    }

    public void setUsername(String username) {
        this.email = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
