package com.bullyrooks.messagegenerator.service;

import com.github.javafaker.Faker;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    Faker faker = new Faker();

    public String getMessage(){
        return faker.gameOfThrones().quote();
    }
}
