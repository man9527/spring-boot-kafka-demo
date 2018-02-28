package com.example.demo.publisher;

import com.example.demo.entity.MessageVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * This publisher accept MessageVO and publish to Kafka topic in Json format
 */
@Component
public final class KafkaJsonPublisher implements Publisher<MessageVO> {

    private static final Logger logger = LoggerFactory.getLogger(KafkaJsonPublisher.class);

    @Value("${kafka.topic.demo}")
    private String topic;

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public KafkaJsonPublisher(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper=objectMapper;
    }

    @Override
    public boolean publish(MessageVO message) {
        if (message==null) {
            logger.warn("Message requested to publish is null");
            return false;
        }

        try {
            String payload = objectMapper.writeValueAsString(message);
            logger.info("Publish Message to json topic:" + message);
            kafkaTemplate.send(topic, payload);
            return true;
        } catch (JsonProcessingException e) {
            logger.error("Message published through KafkaJsonPublisher failed");
            logger.error(e.toString());
            return false;
        } catch (RuntimeException re) {
            logger.error("Message published through KafkaJsonPublisher failed");
            logger.error(re.toString());

            throw re;
        }
    }
}
