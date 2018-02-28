package com.example.demo.rest;

import com.example.demo.entity.MessageVO;
import com.example.demo.rest.request.MessageRequest;
import com.example.demo.services.api.MessagePublishingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class FirstController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MessagePublishingService<MessageVO> messagePublishingService;

    @PostMapping("/message")
    public ResponseEntity<MessageRequest> publishMessage(@Valid @RequestBody MessageRequest messageRequest) {
        MessageVO messageVO = convertToVO(messageRequest);

        messagePublishingService.publish(messageVO);
        
        return new ResponseEntity<>(messageRequest, HttpStatus.OK);

    }

    private MessageVO convertToVO(MessageRequest messageRequest) {
        return modelMapper.map(messageRequest, MessageVO.class);
    }

}
