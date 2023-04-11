package com.project.se2project.domain.User;

import com.project.se2project.model.User;

import java.io.Serializable;

public class GetUserResponse implements Serializable {
    private String name;
    private String username;

    private String dob;

    private boolean isNew;

    private long balance;

    private String startingDate;
    private Long id;

    public GetUserResponse() {
    }

    public GetUserResponse(User user) {
        this.name = user.getName();
        this.username = user.getUsername();
        this.dob = user.getDob();
        this.isNew = user.isNew();
        this.balance = user.getBalance();
        this.startingDate = user.getStartingDate();
        this.id = user.getId();
    }


    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
