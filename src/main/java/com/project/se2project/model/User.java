package com.project.se2project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank(message = "Name cannot be blank")
    @Size(
            min = 8,
            max = 25,
            message = "Name must be between {min} and {max} characters long"
    )
    private String name;

    private Long balance;

    @Size(
            min = 10,
            max = 11,
            message = "Username must be phone number and between {min} and {max} characters long"
    )
    @NotBlank(message = "Username cannot be blank")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Username must be phone number")
    @Column(unique = true)
    private String username;

    private String password;

    public Long getBalance() {
        return balance;
    }

    private String dob;

    private boolean isNew;

    private String type;

    @NotBlank(message = "Starting Date cannot be blank")
    private String startingDate;

    public User() {
    }

    public User(String username, String name, String password, String dob, boolean isNew, String type, Long balance, String startingDate) {
        this.balance = balance;
        this.name = name;
        this.username = username;
        this.password = password;
        this.dob = dob;
        this.isNew = isNew;
        this.type = type;
        this.startingDate = startingDate;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStartingDate() {
        return startingDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStartingDate(String startingDate) {
        this.startingDate = startingDate;
    }
}
