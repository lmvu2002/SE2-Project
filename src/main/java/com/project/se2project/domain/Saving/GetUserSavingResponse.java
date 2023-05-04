package com.project.se2project.domain.Saving;

import com.project.se2project.model.User;

import java.io.Serializable;

public class GetUserSavingResponse implements Serializable {


    private long id;

    private String username;

    private int savingNum;

    public GetUserSavingResponse() {
    }

    public GetUserSavingResponse(User user, int savingNum) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.savingNum = savingNum;
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

    public int getSavingNum() {
        return savingNum;
    }

    public void setSavingNum(int savingNum) {
        this.savingNum = savingNum;
    }
}
