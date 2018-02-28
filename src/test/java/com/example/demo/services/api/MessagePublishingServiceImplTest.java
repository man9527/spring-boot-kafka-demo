package com.example.demo.services.api;

import com.example.demo.entity.MessageVO;
import com.example.demo.publisher.Publisher;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;

import static org.junit.Assert.*;

public class MessagePublishingServiceImplTest {

    @Test
    public void publish() {

        Publisher publisher1 = Mockito.mock(Publisher.class);
        Publisher publisher2 = Mockito.mock(Publisher.class);

        MessagePublishingServiceImpl messagePublishingService = new MessagePublishingServiceImpl(Arrays.asList(publisher1, publisher2));

        MessageVO messageVo = new MessageVO("to", "from", "hello");
        messagePublishingService.publish(messageVo);

        Mockito.verify(publisher1, Mockito.times(1)).publish(messageVo);
        Mockito.verify(publisher2, Mockito.times(1)).publish(messageVo);
    }
}