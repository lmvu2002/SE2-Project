package com.project.se2project.domain.Admin;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AdminSignUpRequest implements Serializable {

    @Size(
            min = 8,
            max = 25,
            message = "Admin name must be between {min} and {max} characters long"
    )
    @NotBlank(message = "Admin name cannot be blank")
    private String adminName;

    @Size(
            min = 5,
            max = 15,
            message = "Password must be between {min} and {max} characters long"
    )
    @NotBlank(message = "Password cannot be blank")
    private String password;

    private String secretKey;

    private String dob;

    public AdminSignUpRequest() {
    }

    public AdminSignUpRequest(String adminName, String password, String secretKey) {
        this.adminName = adminName;
        this.password = password;
        this.secretKey = secretKey;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
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
