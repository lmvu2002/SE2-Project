package com.project.se2project.domain.Deposit;

import com.project.se2project.model.User;

import java.io.Serializable;

public class GetUserDepositResponse implements Serializable {

    private long id;

    private String username;

    private int depositNum;

    public GetUserDepositResponse() {
    }

    public GetUserDepositResponse(User user, int depositNum) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.depositNum = depositNum;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getDepositNum() {
        return depositNum;
    }

    public void setDepositNum(int depositNum) {
        this.depositNum = depositNum;
    }
}
