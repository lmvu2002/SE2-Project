package com.project.se2project.domain.User;

import java.io.Serializable;

public class UserSignInResponse implements Serializable {
    private String jwt;
    private String message;

    public UserSignInResponse() {
    }

    public UserSignInResponse(String jwt, String message) {
        this.jwt = jwt;
        this.message = message;
    }

    public UserSignInResponse(String message) {
        this.message = message;
        this.jwt = "";
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
