package com.project.se2project.domain.Loan;

import com.project.se2project.model.Loan;

import java.io.Serializable;

public class LoanResponse implements Serializable {

    private String jwt;
    private String message;

    public LoanResponse() {
    }

    public LoanResponse(String jwt, String message) {
        this.jwt = jwt;
        this.message = message;
    }

    public LoanResponse(String message) {
        this.message = message;
    }

    public LoanResponse(Loan loan) {
        this.message = "Loan created successfully";
    }

    public Loan GetLoanResponse(Loan loan) {
        return loan;
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
