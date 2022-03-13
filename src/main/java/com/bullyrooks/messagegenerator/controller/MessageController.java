package com.bullyrooks.messagegenerator.controller;

import com.bullyrooks.messagegenerator.config.LoggingEnabled;
import com.bullyrooks.messagegenerator.controller.dto.MessageResponseDTO;
import com.bullyrooks.messagegenerator.controller.mapper.MessageResponseDTOMapper;
import com.bullyrooks.messagegenerator.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@LoggingEnabled
@Slf4j
public class MessageController {
    @Autowired
    MessageService messageService;

    @GetMapping("/message")
    public MessageResponseDTO getMessage(){

        return MessageResponseDTOMapper.INSTANCE.modelToDTO(messageService.getMessage());
    }
}
