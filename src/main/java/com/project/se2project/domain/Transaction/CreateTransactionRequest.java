package com.project.se2project.domain.Transaction;

import jakarta.validation.constraints.NotBlank;
import org.springframework.lang.NonNull;

import java.io.Serializable;

public class CreateTransactionRequest implements Serializable {

    @NonNull
    private Long fromUserId;

    @NonNull
    private Long toUserId;

    @NonNull
    private int amount;

    @NotBlank(message = "Transaction time cannot be blank")
    private String transactionTime;


    public CreateTransactionRequest() {
    }
    public CreateTransactionRequest(Long fromUserId, Long toUserId, int amount, String transactionTime) {
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.amount = amount;
        this.transactionTime = transactionTime;
    }

    public Long getFromUserId() {
        return fromUserId;
    }

    public Long getToUserId() {
        return toUserId;
    }

    public int getAmount() {
        return amount;
    }

    public String getTransactionTime() {
        return transactionTime;
    }
}
