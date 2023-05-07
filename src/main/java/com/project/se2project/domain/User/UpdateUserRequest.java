package com.project.se2project.domain.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public class UpdateUserRequest implements Serializable {

    @NotBlank(message = "User DOB cannot be blank")
    private String dob;


    private String name;

    @NotBlank(message = "User type cannot be blank")
    private String type;

    @NotBlank(message = "User name cannot be blank")
    private String username;

    private boolean isNew;

    public UpdateUserRequest() {
    }

    public UpdateUserRequest(String name, String dob, boolean isNew, String username, String type) {
        this.name = name;
        this.dob = dob;
        this.isNew = isNew;
        this.username = username;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
    


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }
}
