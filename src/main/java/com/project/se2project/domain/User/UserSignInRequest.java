package com.project.se2project.domain.User;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

public class UserSignInRequest implements Serializable {
    @NotBlank(message = "Username cannot be blank")
    private String username;

    @NotBlank(message = "Password cannot be blank")
    private String password;

    public UserSignInRequest() {
    }

    public UserSignInRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
