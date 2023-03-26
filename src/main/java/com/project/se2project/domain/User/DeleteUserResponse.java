package com.project.se2project.domain.User;

import com.project.se2project.model.User;

import java.io.Serializable;

public class DeleteUserResponse implements Serializable {
    private String message;

    public DeleteUserResponse() {
    }

    public DeleteUserResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
