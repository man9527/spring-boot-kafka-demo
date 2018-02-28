package com.example.demo.publisher;

import com.example.demo.avro.AvroMessage;
import com.example.demo.entity.MessageVO;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.Assert.*;
import static org.mockito.Matchers.eq;

public class KafkaAvroPublisherTest {

    @Test
    public void publish() {
        final String testtopic = "testtopic";

        KafkaTemplate<String, AvroMessage> kafkaTemplate = Mockito.mock(KafkaTemplate.class);

        KafkaAvroPublisher kafkaAvroPublisher = new KafkaAvroPublisher(kafkaTemplate);

        ReflectionTestUtils.setField(kafkaAvroPublisher, "topic", testtopic);

        MessageVO messageVO = new MessageVO("to", "from", "message");

        kafkaAvroPublisher.publish(messageVO);

        AvroMessage avroMessage = AvroMessage.newBuilder().setTo(messageVO.getTo())
                .setFrom(messageVO.getFrom()).setMessage(messageVO.getMessage()).build();

        Mockito.verify(kafkaTemplate, Mockito.times(1)).send(eq(testtopic), eq(avroMessage));
    }
}