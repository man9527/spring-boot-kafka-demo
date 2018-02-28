package com.example.demo.entity;

import java.util.Objects;

public final class MessageVO {

    private String to;

    private String from;

    private String message;

    public MessageVO() {}
    
    public MessageVO(String to, String from, String message) {
        this.to = to;
        this.from = from;
        this.message = message;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageVO messageVO = (MessageVO) o;
        return Objects.equals(to, messageVO.to) &&
                Objects.equals(from, messageVO.from) &&
                Objects.equals(message, messageVO.message);
    }

    @Override
    public int hashCode() {

        return Objects.hash(to, from, message);
    }

    @Override
    public String toString() {
        return "MessageVO{" +
                "to='" + to + '\'' +
                ", from='" + from + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
