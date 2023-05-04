package com.project.se2project.model;

import groovyjarjarpicocli.CommandLine;
import jakarta.persistence.*;
import org.springframework.lang.NonNull;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Entity
@Table(name = "transaction")
public class Transaction {
    private static Set<Long> generatedIds = new HashSet<>();

    @Id
    @Column(name = "transaction_id", nullable = false)
    private long transactionId;

    @PrePersist
    public void generateId() {
        Random random = new Random();
        long newId;
        do {
            newId = random.nextLong(90000) + 10000;
        } while (generatedIds.contains(newId));
        generatedIds.add(newId);
        this.transactionId = newId;
    }

    @NonNull
    private Long fromUserId;
    @NonNull
    private Long toUserId;
    @NonNull
    private int amount;
    @NonNull
    private String transactionTime;

    public Transaction(@NonNull Long fromUserId, @NonNull Long toUserId, int amount,@NonNull String transactionTime) {
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.amount = amount;
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
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
