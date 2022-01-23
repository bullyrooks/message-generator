package com.bullyrooks.messagegenerator.controller;

import com.bullyrooks.messagegenerator.controller.dto.MessageResponseDTO;
import com.bullyrooks.messagegenerator.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {
    @Autowired
    MessageService messageService;

    @GetMapping("/message")
    public MessageResponseDTO getMessage(){

        return MessageResponseDTO.builder()
                .message(messageService.getMessage())
                .build();
    }
}
