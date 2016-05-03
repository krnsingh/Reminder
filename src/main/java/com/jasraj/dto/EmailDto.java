package com.jasraj.dto;

public class EmailDto {

    private String email;
    private String message;

    public String getEmail() {
        return email;
    }

    public EmailDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public EmailDto setMessage(String message) {
        this.message = message;
        return this;
    }
}
