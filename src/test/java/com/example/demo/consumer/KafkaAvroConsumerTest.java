package com.example.demo.consumer;

import com.example.demo.avro.AvroMessage;
import org.junit.Test;

import static org.junit.Assert.*;

public class KafkaAvroConsumerTest {

    @Test
    public void listen() {
        KafkaAvroConsumer kafkaAvroConsumer = new KafkaAvroConsumer();
        AvroMessage avroMessage = AvroMessage.newBuilder()
                .setTo("to")
                .setFrom("from")
                .setMessage("message")
                .build();

        kafkaAvroConsumer.listen(avroMessage);
    }
}