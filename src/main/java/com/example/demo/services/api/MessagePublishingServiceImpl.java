package com.example.demo.services.api;

import com.example.demo.entity.MessageVO;
import com.example.demo.publisher.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * This publisher service is a gateway to relay message to all publishers
 */
@Component
public final class MessagePublishingServiceImpl implements MessagePublishingService<MessageVO> {

    private static final Logger logger = LoggerFactory.getLogger(MessagePublishingServiceImpl.class);

    private final List<Publisher<MessageVO>> publishers;

    @Autowired
    public MessagePublishingServiceImpl(final List<Publisher<MessageVO>> publishers) {
        this.publishers = publishers;
    }

    @Override
    public boolean publish(MessageVO message) {
        try {
            publishers.forEach(p -> p.publish(message));
            return true;
        } catch (Exception e) {
            logger.error(e.toString());
            return false;
        }
    }
}
