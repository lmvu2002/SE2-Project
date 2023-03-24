package com.project.se2project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.NonNull;

@Entity
public class Admin {

    @Id
    @NonNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    @NonNull
    @Length(min=8, max=25)
    private String adminname;

    @NonNull
    @Length(min=5, max=15)
    private String password;

    @NonNull
    private String dob;


    @NonNull
    public String getDob() {
        return dob;
    }

    public void setDob(@NonNull String dob) {
        this.dob = dob;
    }

    @NonNull
    public String getAdminname() {
        return adminname;
    }

    public void setUsername(@NonNull String username) {
        this.adminname = username.toUpperCase();
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
        return "id: "+getId()+"// username: "+getAdminname();
    }
}
