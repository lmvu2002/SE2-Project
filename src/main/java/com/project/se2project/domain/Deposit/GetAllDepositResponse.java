package com.project.se2project.domain.Deposit;

import java.io.Serializable;
import java.util.List;

public class GetAllDepositResponse implements Serializable {

    private String message;

    private List<GetDepositResponse> allDeposit;

    public GetAllDepositResponse(List<GetDepositResponse> allDeposit) {
        this.allDeposit = allDeposit;
        this.message = "OK";
    }

    public GetAllDepositResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
