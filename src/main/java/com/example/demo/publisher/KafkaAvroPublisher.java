package com.example.demo.publisher;

import com.example.demo.avro.AvroMessage;
import com.example.demo.entity.MessageVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * This publisher accept MessageVO and publish to Kafka topic in Avro format
 */
@Component
public final class KafkaAvroPublisher implements Publisher<MessageVO> {

    private static final Logger logger = LoggerFactory.getLogger(KafkaAvroPublisher.class);

    @Value("${kafka.topic.demo.avro}")
    private String topic;

    private final KafkaTemplate<String, AvroMessage> kafkaAvroTemplate;

    @Autowired
    public KafkaAvroPublisher(KafkaTemplate<String, AvroMessage> kafkaAvroTemplate) {
        this.kafkaAvroTemplate = kafkaAvroTemplate;
    }

    @Override
    public boolean publish(MessageVO message) {

        if (message == null) {
            logger.warn("Message requested to publish is null");
            return false;
        }

        try {
            AvroMessage avroMessage = AvroMessage.newBuilder()
                    .setTo(message.getTo())
                    .setFrom(message.getFrom())
                    .setMessage(message.getMessage())
                    .build();

            logger.info("Publish Message to avro topic:" + message);
            kafkaAvroTemplate.send(topic, avroMessage);
            return true;
        } catch (Exception e) {
            logger.error("Message published through KafkaAvroPublisher failed");
            logger.error(e.toString());

            throw e;
        }

    }
}
