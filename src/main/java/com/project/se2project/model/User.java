package com.project.se2project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.NonNull;

@Entity
public class User {

    @Id
    @NonNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NonNull
    @Min(0)
    private Long balance;

    @NonNull
    @Length(min=8, max=25)
    private String username;

    @NonNull
    @Length(min=5, max=15)
    private String password;

    @NonNull
    public Long getBalance() {
        return balance;
    }

    public void add(@NonNull Long amount) {
        this.balance = this.balance+amount;
    }

    public void minus(@NonNull Long amount) {
        this.balance = this.balance-amount;
    }

    @NonNull
    public String getUsername() {
        return username;
    }

    public void setUsername(@NonNull String username) {
        this.username = username.toUpperCase();
    }

    @NonNull
    public String getPassword() {
        return password;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "id: "+getId()+"// username: "+getUsername()+"// balance: "+getBalance();
    }
}
