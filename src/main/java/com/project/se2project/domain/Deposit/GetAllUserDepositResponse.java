package com.project.se2project.domain.Deposit;

import java.util.List;

public class GetAllUserDepositResponse {

    private String message;

    private List<GetUserDepositResponse> allHasUser;

    public GetAllUserDepositResponse() {
    }

    public GetAllUserDepositResponse(List<GetUserDepositResponse> allHasUser) {
        this.allHasUser = allHasUser;
        this.message = "OK";
    }

    public GetAllUserDepositResponse(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<GetUserDepositResponse> getAllHasUser() {
        return allHasUser;
    }

    public void setAllHasUser(List<GetUserDepositResponse> allHasUser) {
        this.allHasUser = allHasUser;
    }
}
