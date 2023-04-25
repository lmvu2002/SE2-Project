package com.project.se2project.domain.Transaction;

import java.io.Serializable;
import java.util.List;

public class GetAllTransactionResponse implements Serializable {
    private List<GetTransactionResponse> allTransaction;

    private String message;

    public GetAllTransactionResponse() {
    }

    public GetAllTransactionResponse(List<GetTransactionResponse> allTransaction) {
        this.allTransaction = allTransaction;
        this.message = "OK";
    }

    public GetAllTransactionResponse(String message) {
        this.allTransaction = null;
        this.message = message;
    }

    public List<GetTransactionResponse> getAllTransaction() {
        return allTransaction;
    }

    public void setAllTransaction(List<GetTransactionResponse> allTransaction) {
        this.allTransaction = allTransaction;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
