package com.project.se2project.domain.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public class UserSignUpRequest implements Serializable {
    private Long balance;

    @Size(
            min = 10,
            max = 11,
            message = "User name must be phone number and between {min} and {max} characters long"
    )
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Username must be phone number")
    private String username;

    @NotBlank(message = "Name cannot be blank")
    @Size(
            min = 8,
            max = 25,
            message = "Name must be between {min} and {max} characters long"
    )
    private String name;

    @Size(
            min = 5,
            max = 15,
            message = "User password must be between {min} and {max} characters long"
    )
    @NotBlank(message = "User password cannot be blank")
    private String password;

    @NotBlank(message = "User DOB cannot be blank")
    private String dob;

    private boolean isNew;

    public UserSignUpRequest() {
    }

    public UserSignUpRequest(String username, String name, String password, String dob, Long balance) {
        this.username = username;
        this.name = name;
        this.password = password;
        this.dob = dob;
        this.balance = balance;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
