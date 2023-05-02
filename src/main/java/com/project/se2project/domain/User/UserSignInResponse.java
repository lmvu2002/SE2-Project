package com.project.se2project.domain.User;

import java.io.Serializable;

public class UserSignInResponse implements Serializable {
    private String jwt;
    private String message;
    private boolean isAdmin;

    private long id;

    public UserSignInResponse() {
    }

    public UserSignInResponse(long id, String jwt, String message, boolean isAdmin) {
        this.jwt = jwt;
        this.message = message;
        this.isAdmin = isAdmin;
        this.id = id;
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

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
