package com.project.se2project.model;

import groovyjarjarpicocli.CommandLine;
import jakarta.persistence.*;
import org.springframework.lang.NonNull;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id", nullable = false)
    private Long transactionId;
    @NonNull
    private Long fromUserId;
    @NonNull
    private Long toUserId;
    @NonNull
    private String transactionTime;

    public Transaction(@NonNull Long fromUserId, @NonNull Long toUserId,@NonNull String transactionTime) {
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.transactionTime = transactionTime;
    }

    public Transaction() {

    }

    public Long getTransactionId() {
        return transactionId;
    }

    @NonNull
    public Long getFromUserId() {
        return fromUserId;
    }

    @NonNull
    public Long getToUserId() {
        return toUserId;
    }

    @NonNull
    public String getTransactionTime() {
        return transactionTime;
    }

    public void setFromUserId(@NonNull Long fromUserId) {
        this.fromUserId = fromUserId;
    }

    public void setToUserId(@NonNull Long toUserId) {
        this.toUserId = toUserId;
    }

    public void setTransactionTime(@NonNull String transactionTime) {
        this.transactionTime = transactionTime;
    }
}
