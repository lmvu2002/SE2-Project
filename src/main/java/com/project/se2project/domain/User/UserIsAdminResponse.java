package com.project.se2project.domain.User;

import java.io.Serializable;

public class UserIsAdminResponse implements Serializable {
    private boolean isAdmin;
    private String message;

    public UserIsAdminResponse() {
    }

    public UserIsAdminResponse(boolean isAdmin) {
        this.isAdmin = isAdmin;
        this.message = "User is" + (isAdmin ? " " : " not ") + "admin";
    }

    public UserIsAdminResponse(String message) {
        this.isAdmin = false;
        this.message = message;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
