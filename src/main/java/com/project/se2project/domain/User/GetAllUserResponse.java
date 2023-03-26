package com.project.se2project.domain.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GetAllUserResponse implements Serializable {
    private List<GetUserResponse> allUser;

    private String message;

    public GetAllUserResponse() {
    }

    public GetAllUserResponse(List<GetUserResponse> allUser) {
        this.allUser = allUser;
        this.message = "OK";
    }

    public GetAllUserResponse(String message) {
        this.allUser = new ArrayList<>();
        this.message = message;
    }

    public List<GetUserResponse> getAllUser() {
        return allUser;
    }

    public void setAllUser(List<GetUserResponse> allUser) {
        this.allUser = allUser;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
