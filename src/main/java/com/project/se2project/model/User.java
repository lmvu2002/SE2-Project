package com.project.se2project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.NonNull;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private Long balance;

    @Size(
            min = 8,
            max = 25,
            message = "Username must be between {min} and {max} characters long"
    )
    private String username;

    private String password;

    public Long getBalance() {
        return balance;
    }

    private String dob;

    private boolean isNew;

    private String phone;

    private String type;

    public User() {
    }

    public User(String username, String password, String phone, String dob, boolean isNew, String type, Long balance) {
        this.balance = balance;
        this.username = username;
        this.password = password;
        this.dob = dob;
        this.isNew = isNew;
        this.phone = phone;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
