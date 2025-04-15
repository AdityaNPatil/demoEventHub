package com.example.demoEventHub.pdfa;

import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.messaging.eventhubs.EventHubClientBuilder;
import com.azure.messaging.eventhubs.EventHubConsumerAsyncClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class EventHubConsumerLowLevel {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventHubConsumerLowLevel.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @PostConstruct
    public void startListening() {
        String namespace = "pdfa-dev-namespace.servicebus.windows.net";
        String eventHubName = "pdfa-eventhub";

        EventHubConsumerAsyncClient consumer = new EventHubClientBuilder()
                .fullyQualifiedNamespace(namespace)
                .eventHubName(eventHubName)
                .consumerGroup("$Default")
                .credential(new DefaultAzureCredentialBuilder().managedIdentityClientId("a2e0e349-9b2c-44fb-909b-4fe1a4685588").build())
                .buildAsyncConsumerClient();

        LOGGER.info("PDF/A Low Level Consumer Started...");

        consumer.receive(false)
                .subscribe(partitionEvent -> {
                    try {
                        String payload = partitionEvent.getData().getBodyAsString();
                        PdfaEvent event = objectMapper.readValue(payload, PdfaEvent.class);
                        LOGGER.info("JobID={}, Status={}, CustomerId={}", event.getConversionJobId(), event.getStatus(), event.getCustomerId());
                    } catch (Exception e) {
                        LOGGER.error("Failed to process message", e);
                    }
        });
    }
}
