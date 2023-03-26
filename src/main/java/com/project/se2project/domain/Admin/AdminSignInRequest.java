package com.project.se2project.domain.Admin;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

public class AdminSignInRequest implements Serializable {

    @NotBlank(message = "Admin name cannot be blank")
    private String adminName;

    @NotBlank(message = "Password cannot be blank")
    private String password;

    public AdminSignInRequest() {
    }

    public AdminSignInRequest(String adminName, String password) {
        this.adminName = adminName;
        this.password = password;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
