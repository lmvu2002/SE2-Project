package com.project.se2project.domain.User;

import java.io.Serializable;

public class UserSignUpResponse implements Serializable {
    private String jwt;
    private String message;

    public UserSignUpResponse() {
    }

    public UserSignUpResponse(String jwt, String message) {
        this.jwt = jwt;
        this.message = message;
    }

    public UserSignUpResponse(String message) {
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
