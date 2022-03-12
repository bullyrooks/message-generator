package com.bullyrooks.messagegenerator.controller;

import com.bullyrooks.messagegenerator.controller.dto.MessageResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
@Tag("UnitTest")
@AutoConfigureMockMvc
public class MessageControllerTest {
    @LocalServerPort
    int randomServerPort;

    @Test
    void testAddCustomerSuccess() throws URISyntaxException, JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        String baseUrl = "http://localhost:" + randomServerPort + "/message";
        URI uri = new URI(baseUrl);

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        ResponseEntity<MessageResponseDTO> result = restTemplate.getForEntity(uri, MessageResponseDTO.class);

        //Verify request succeed
        assertEquals(200, result.getStatusCodeValue());
        MessageResponseDTO response = result.getBody();
        log.info("Test message returned: {}",response.getMessage());
        assertTrue(StringUtils.isNotBlank(response.getMessage()));
    }
}
