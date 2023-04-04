package com.project.se2project.domain.User;

import java.io.Serializable;

public class UserSignInResponse implements Serializable {
    private String jwt;
    private String message;

    private boolean isAdmin;

    public UserSignInResponse() {
    }

    public UserSignInResponse(String jwt, String message, boolean isAdmin) {
        this.jwt = jwt;
        this.message = message;
        this.isAdmin = isAdmin;
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

    public boolean isAdmin() {
        return isAdmin;
    }
}
