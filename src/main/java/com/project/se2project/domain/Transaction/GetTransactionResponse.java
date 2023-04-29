package com.project.se2project.domain.Transaction;

import com.project.se2project.domain.User.GetUserResponse;
import com.project.se2project.model.Transaction;
import org.springframework.lang.NonNull;

import java.io.Serializable;

public class GetTransactionResponse implements Serializable {
    private Long transactionId;

    private Long fromUserId;

    private Long toUserId;

    private int amount;

    private String transactionTime;

    private String text;
    public GetTransactionResponse(Transaction transaction) {
        this.transactionId = transaction.getTransactionId();
        this.fromUserId = transaction.getFromUserId();
        this.toUserId = transaction.getToUserId();
        this.amount = transaction.getAmount();
        this.transactionTime = transaction.getTransactionTime();
    }

    public GetTransactionResponse(String text) {
        this.text = text;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public Long getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(Long fromUserId) {
        this.fromUserId = fromUserId;
    }

    public Long getToUserId() {
        return toUserId;
    }

    public void setToUserId(Long toUserId) {
        this.toUserId = toUserId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(String transactionTime) {
        this.transactionTime = transactionTime;
    }
}
