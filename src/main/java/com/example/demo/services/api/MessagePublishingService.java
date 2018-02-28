package com.example.demo.services.api;

public interface MessagePublishingService<T> {
    boolean publish(T message);
}
