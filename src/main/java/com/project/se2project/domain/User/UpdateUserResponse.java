package com.project.se2project.domain.User;

import com.project.se2project.model.User;

import java.io.Serializable;

public class UpdateUserResponse implements Serializable {
    private String message;
    private User user;

    public UpdateUserResponse() {
    }

    public UpdateUserResponse(String message) {
        this.message = message;
        this.user = null;
    }

    public UpdateUserResponse(User user) {
        this.user = user;
        this.message = "OK";
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
