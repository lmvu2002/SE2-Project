package com.project.se2project.domain.User;

import org.springframework.lang.NonNull;

public class GetNameRequest {

    @NonNull
    private String username;

    public GetNameRequest(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
