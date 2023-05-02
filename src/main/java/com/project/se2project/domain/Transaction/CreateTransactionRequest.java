package com.project.se2project.domain.Transaction;

import jakarta.validation.constraints.NotBlank;
import org.springframework.lang.NonNull;

import java.io.Serializable;

public class CreateTransactionRequest implements Serializable {

    @NonNull
    private String fromUserUsername;

    @NonNull
    private String toUserUsername;

    @NonNull
    private int amount;

    @NotBlank(message = "Transaction time cannot be blank")
    private String transactionTime;


    public CreateTransactionRequest() {
    }
    public CreateTransactionRequest(String fromUserUsername, String toUserUsername, int amount, String transactionTime) {
        this.fromUserUsername = fromUserUsername;
        this.toUserUsername = toUserUsername;
        this.amount = amount;
        this.transactionTime = transactionTime;
    }

    public String getFromUserUsername() {
        return fromUserUsername;
    }

    public String getToUserUsername() {
        return toUserUsername;
    }

    public int getAmount() {
        return amount;
    }

    public String getTransactionTime() {
        return transactionTime;
    }
}
