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

    @NotBlank(message = "User phone cannot be blank")
    private String phone;
    private String name;

    public UpdateUserRequest() {
    }

    public UpdateUserRequest(long balance, String dob, String phone, String name) {
        this.balance = balance;
        this.dob = dob;
        this.phone = phone;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
