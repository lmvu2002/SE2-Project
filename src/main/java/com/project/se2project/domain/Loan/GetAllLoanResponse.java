package com.project.se2project.domain.Loan;

import java.io.Serializable;
import java.util.List;

public class GetAllLoanResponse implements Serializable {
    private List<GetLoanResponse> allLoan;

    private String message;
    public GetAllLoanResponse() {
    }

    public GetAllLoanResponse(List<GetLoanResponse> allLoan) {
        this.allLoan = allLoan;
        this.message = "OK";
    }

    public GetAllLoanResponse(String message) {
        this.allLoan = null;
        this.message = message;
    }

    public List<GetLoanResponse> getAllLoan() {
        return allLoan;
    }

    public void setAllLoan(List<GetLoanResponse> allLoan) {
        this.allLoan = allLoan;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
