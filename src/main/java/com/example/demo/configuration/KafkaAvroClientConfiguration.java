package com.example.demo.configuration;

import com.example.demo.avro.AvroMessage;
import com.example.demo.utils.AvroDeserializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import java.util.HashMap;
import java.util.Map;

/**
 * This configuration config the kafka client for Avro data format
 */
@Configuration
public class KafkaAvroClientConfiguration {

    @Value("${kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${kafka.client.group.name.avro}")
    private String groupName;

    @Bean
    public Map<String, Object> avroConsumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapServers);
        props.put(
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class);
        props.put(
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                AvroDeserializer.class);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupName);

        return props;
    }

    @Bean
    public ConsumerFactory<String, AvroMessage> avroConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(
                avroConsumerConfigs(),
                new StringDeserializer(),
                new AvroDeserializer(AvroMessage.class));
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, AvroMessage>> kafkaAvroListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, AvroMessage> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(avroConsumerFactory());

        return factory;
    }
}
