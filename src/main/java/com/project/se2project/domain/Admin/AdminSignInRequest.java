package com.project.se2project.domain.Admin;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

public class AdminSignInRequest implements Serializable {

    @NotBlank(message = "Admin name cannot be blank")
    private String name;

    @NotBlank(message = "Password cannot be blank")
    private String password;

    public AdminSignInRequest() {
    }

    public AdminSignInRequest(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
