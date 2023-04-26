package com.project.se2project.domain.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public class UpdateUserRequest implements Serializable {
    private long balance;

    @NotBlank(message = "User DOB cannot be blank")
    private String dob;

    private boolean isNew;

    @NotBlank(message = "User phone cannot be blank")
    private String phone;

    @NotBlank(message = "User type cannot be blank")
    private String type;


    @NotBlank(message = "User name cannot be blank")
    private String username;
    public UpdateUserRequest() {
    }

    public UpdateUserRequest(long balance, String dob, boolean isNew, String username, String phone, String type) {
        this.balance = balance;
        this.dob = dob;
        this.isNew = isNew;
        this.phone = phone;
        this.username = username;
        this.type = type;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
