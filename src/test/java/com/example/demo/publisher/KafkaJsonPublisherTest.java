package com.example.demo.publisher;

import com.example.demo.entity.MessageVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.Matchers.eq;

public class KafkaJsonPublisherTest {

    @Test
    public void publish() throws JsonProcessingException {

        final String testtopic = "testtopic";

        KafkaTemplate<String, String> kafkaTemplate = Mockito.mock(KafkaTemplate.class);

        KafkaJsonPublisher kafkaJsonPublisher = new KafkaJsonPublisher(kafkaTemplate, new ObjectMapper());

        ReflectionTestUtils.setField(kafkaJsonPublisher, "topic", testtopic);

        MessageVO messageVO = new MessageVO("to", "from", "message");

        kafkaJsonPublisher.publish(messageVO);

        String message = new ObjectMapper().writeValueAsString(messageVO);

        Mockito.verify(kafkaTemplate, Mockito.times(1))
                .send(eq(testtopic), eq(message));
    }
}