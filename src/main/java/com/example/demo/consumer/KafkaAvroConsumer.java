package com.example.demo.consumer;

import com.example.demo.avro.AvroMessage;
import com.example.demo.entity.MessageVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * This consumer listens to a topic and consume data in Avro format
 */
@Component
public final class KafkaAvroConsumer {

    private static final Logger logger = LoggerFactory.getLogger(KafkaAvroConsumer.class);

    @KafkaListener(topics = "#{'${kafka.topic.demo.avro}'}", group = "#{'${kafka.client.group.name.avro}'}",
            containerFactory = "kafkaAvroListenerContainerFactory")
    public void listen(AvroMessage message) {
        try {
            logger.info("Received Messasge in avro consumer: " + message);
        } catch (Exception e) {
            logger.warn("Can't not conver json string to expected type MessageVO");
            logger.warn("Message Received:" + message);
        }
    }
}
