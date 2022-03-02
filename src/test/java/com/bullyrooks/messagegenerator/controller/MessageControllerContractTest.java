package com.bullyrooks.messagegenerator.controller;

import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junitsupport.State;
import au.com.dius.pact.provider.junitsupport.loader.PactBroker;
import au.com.dius.pact.provider.junitsupport.loader.PactBrokerAuth;
import au.com.dius.pact.provider.spring.junit5.PactVerificationSpringProvider;
import com.bullyrooks.messagegenerator.service.MessageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;


@Provider(MessageControllerContractTest.PROVIDER)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@PactBroker(consumerVersionSelectors={
//        @VersionSelector(tag = "placeholder-pact-matrix-mapping")})

/* Placeholder configs for connecting to Pactflow */
@PactBroker(
        host = "bullyrooks.pactflow.io", scheme = "https",
        authentication = @PactBrokerAuth(token = "${pactbroker.auth.token}"))
@AutoConfigureMockMvc
public class MessageControllerContractTest {
    final static String PROVIDER = "message-generator";
    @LocalServerPort
    private int port;

    @MockBean
    MessageService service;

    @BeforeEach
    void setup(PactVerificationContext context) {
        context.setTarget(new HttpTestTarget("localhost", port));
    }

    @TestTemplate
    @ExtendWith(PactVerificationSpringProvider.class)
    void pactVerificationTestTemplate(PactVerificationContext context) {
        context.verifyInteraction();
    }


    @State("generator creates a message")
    public void shouldReturnMessage() {
        //@formatter:off
        Mockito.when(service.getMessage()).thenReturn("All dwarfs are bastards in their father's eyes");
        //@formatter:on
    }
}
