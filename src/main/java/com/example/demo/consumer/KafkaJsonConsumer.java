package com.example.demo.consumer;

import com.example.demo.entity.MessageVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * This consumer listens to a topic and consume data in Json format
 */
@Component
public final class KafkaJsonConsumer {

    private static final Logger logger = LoggerFactory.getLogger(KafkaJsonConsumer.class);

    private final ObjectMapper objectMapper;

    @Autowired
    public KafkaJsonConsumer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "#{'${kafka.topic.demo}'}", group = "#{'${kafka.client.group.name}'}")
    public void listen(String message) {

        try {
            MessageVO messageVO = objectMapper.readValue(message, MessageVO.class);
            logger.info("Received Messasge in json consumer: " + messageVO);
        } catch (Exception e) {
            logger.warn("Can't not conver json string to expected type MessageVO");
            logger.warn("Message Received:" + message);
        }
    }
}
