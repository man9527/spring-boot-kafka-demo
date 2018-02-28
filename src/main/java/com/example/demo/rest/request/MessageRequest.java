package com.example.demo.rest.request;

import org.hibernate.validator.constraints.NotEmpty;

public final class MessageRequest {

    @NotEmpty
    private String to;

    @NotEmpty
    private String from;

    @NotEmpty
    private String message;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
