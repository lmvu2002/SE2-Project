package com.project.se2project.domain.Saving;

import java.io.Serializable;
import java.util.List;

public class GetAllSavingResponse implements Serializable {

    private List<GetSavingResponse> allSaving;

    private String message;

    public GetAllSavingResponse(List<GetSavingResponse> allSaving) {
        this.allSaving = allSaving;
        this.message = "OK";
    }

    public GetAllSavingResponse(String message) {
        this.message = message;
    }

    public List<GetSavingResponse> getAllSaving() {
        return allSaving;
    }

    public void setAllLoan(List<GetSavingResponse> allSaving) {
        this.allSaving = allSaving;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
