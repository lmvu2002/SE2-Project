package com.project.se2project.domain.User;

import com.project.se2project.model.User;

import java.io.Serializable;

public class GetUserResponse implements Serializable {
    private String username;

    private String phone;

    private String dob;

    private String type;

    private long balance;

    private String startingDate;

    public GetUserResponse() {
    }

    public GetUserResponse(User user) {
        this.username = user.getUsername();
        this.phone = user.getPhone();
        this.dob = user.getDob();
        this.type = user.getType();
        this.balance = user.getBalance();
        this.startingDate = user.getStartingDate();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public String getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(String startingDate) {
        this.startingDate = startingDate;
    }
}
