package com.example.demo.consumer;

import com.example.demo.entity.MessageVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;

public class KafkaJsonConsumerTest {

    @Test
    public void listen() throws IOException {
        ObjectMapper objectMapper = Mockito.mock(ObjectMapper.class);

        KafkaJsonConsumer kafkaJsonConsumer = new KafkaJsonConsumer(objectMapper);

        String message = "{\"to\": \"to\", \"from\": \"from\", \"message\": \"message\"}";

        kafkaJsonConsumer.listen(message);

        Mockito.verify(objectMapper, times(1))
                .readValue(message, MessageVO.class);
    }
}