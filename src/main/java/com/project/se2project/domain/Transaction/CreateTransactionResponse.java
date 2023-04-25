package com.project.se2project.domain.Transaction;

import java.io.Serializable;

public class CreateTransactionResponse implements Serializable {
    private String jwt;
    private String message;

    public CreateTransactionResponse(String message) {
        this.message = message;
        this.jwt = "";
    }

    public CreateTransactionResponse(String jwt, String message) {
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
