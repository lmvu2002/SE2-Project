package com.project.se2project.domain.Admin;

import java.io.Serializable;

public class AdminSignInResponse implements Serializable {
    private String jwt;
    private String message;


    public AdminSignInResponse(String message) {
        this.message = message;
        this.jwt = "";
    }

    public AdminSignInResponse(String jwt, String message) {
        this.jwt = jwt;
        this.message = message;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getJwt() {
        return jwt;
    }

    public String getMessage() {
        return message;
    }
}
