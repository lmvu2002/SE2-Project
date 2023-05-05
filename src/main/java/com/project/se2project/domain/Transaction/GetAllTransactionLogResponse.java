package com.project.se2project.domain.Transaction;

import java.io.Serializable;
import java.util.List;

public class GetAllTransactionLogResponse implements Serializable {
    private List<GetTransactionLogResponse> allTransactionLog;

    private String message;

    public GetAllTransactionLogResponse() {
    }

    public GetAllTransactionLogResponse(String message) {
        this.message = message;
    }


    public GetAllTransactionLogResponse(List<GetTransactionLogResponse> allTransactionLog) {
        this.allTransactionLog = allTransactionLog;
        this.message = "OK";
    }

    public List<GetTransactionLogResponse> getAllTransactionLog() {
        return allTransactionLog;
    }

    public String getMessage() {
        return message;
    }
}
