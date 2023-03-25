package com.project.se2project.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.time.format.DateTimeFormatter;

@Entity
public class Transaction {
    @Id
    @NonNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NonNull
    @Column(name = "sender", nullable = false)
    private Long sender;

    @NonNull
    @Column(name = "receiver", nullable = false)
    private Long receiver;

    @NonNull
    @Column(name = "amount", nullable = false)
    private Long amount;

    @NonNull
    @Column(name = "date", nullable = false)
    private String date;

    @Nullable
    @Column(name = "note", nullable = true)
    private String note;

    @NonNull
    public Long getId() {
        return id;
    }

    public void setId(@NonNull Long id) {
        this.id = id;
    }

    @NonNull
    public Long getSender() {
        return sender;
    }

    public void setSender(@NonNull Long sender) {
        this.sender = sender;
    }

    @NonNull
    public Long getReceiver() {
        return receiver;
    }

    public void setReceiver(@NonNull Long receiver) {
        this.receiver = receiver;
    }

    @NonNull
    public Long getAmount() {
        return amount;
    }

    public void setAmount(@NonNull Long amount) {
        this.amount = amount;
    }

    @NonNull
    public String getDate() {
        return date;
    }

    public void setDate(@NonNull String date) {
        this.date = date;
    }

    @Nullable
    public String getNote() {
        return note;
    }

    public void setNote(@Nullable String note) {
        this.note = note;
    }
}
