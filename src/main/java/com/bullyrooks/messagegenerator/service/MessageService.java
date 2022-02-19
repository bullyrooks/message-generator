package com.bullyrooks.messagegenerator.service;

import com.bullyrooks.messagegenerator.config.LoggingEnabled;
import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@LoggingEnabled
@Slf4j
public class MessageService {
    Faker faker = new Faker();

    public String getMessage(){
        return faker.gameOfThrones().quote();
    }
}
