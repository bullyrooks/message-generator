package com.bullyrooks.messagegenerator.controller;

import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junitsupport.IgnoreNoPactsToVerify;
import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junitsupport.State;
import au.com.dius.pact.provider.junitsupport.loader.PactBroker;
import au.com.dius.pact.provider.junitsupport.loader.PactBrokerAuth;
import au.com.dius.pact.provider.junitsupport.loader.VersionSelector;
import au.com.dius.pact.provider.spring.junit5.PactVerificationSpringProvider;
import com.bullyrooks.messagegenerator.service.MessageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;


@Provider(MessageControllerContractIT.PROVIDER)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@PactBroker(
        authentication = @PactBrokerAuth(token = "${PACTFLOW_TOKEN}"),
        consumerVersionSelectors = {
                @VersionSelector(tag = "${PACT_CONSUMER_SELECTOR_TAG:okteto}")
        }
)

@AutoConfigureMockMvc
@Tag("ContractTest")
@IgnoreNoPactsToVerify
public class MessageControllerContractIT {
    final static String PROVIDER = "message-generator";
    @MockBean
    MessageService service;
    @LocalServerPort
    private int port;

    @BeforeEach
    void setup(PactVerificationContext context) {
        if (null!=context) {
            context.setTarget(new HttpTestTarget("localhost", port));
        }
    }

    @TestTemplate
    @ExtendWith(PactVerificationSpringProvider.class)
    void pactVerificationTestTemplate(PactVerificationContext context) {
        if (null!=context) {
            context.verifyInteraction();
        }
    }


    @State("generator creates a message")
    public void shouldReturnMessage() {
        //@formatter:off
        Mockito.when(service.getMessage()).thenReturn("All dwarfs are bastards in their father's eyes");
        //@formatter:on
    }
}
