package com.project.se2project.domain.User;

public class GetNameResponse {
    private String name;

    private String text;
    public GetNameResponse(String name) {
        this.name = name;
    }

    public GetNameResponse() {
        setText("No user found!");
    }
    public String getName() {
        return name;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
