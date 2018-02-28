package com.example.demo.publisher;

public interface Publisher<T> {

    boolean publish(T message);

}
