package com.project.se2project.domain.Saving;

import java.io.Serializable;
import java.util.List;

public class GetAllUserSavingResponse implements Serializable {

    private String message;

    private List<GetUserSavingResponse> allHasUser;

    public GetAllUserSavingResponse() {
    }

    public GetAllUserSavingResponse(List<GetUserSavingResponse> allHasUser) {
        this.allHasUser = allHasUser;
        this.message = "OK";
    }

    public GetAllUserSavingResponse(String message) {
        this.allHasUser = null;
        this.message = message;
    }

    public List<GetUserSavingResponse> getAllHasUser() {
        return allHasUser;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setAllHasUser(List<GetUserSavingResponse> allHasUser) {
        this.allHasUser = allHasUser;
    }
}
