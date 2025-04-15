package com.example.demoEventHub.pdfa;

import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.messaging.eventhubs.EventData;
import com.azure.messaging.eventhubs.EventHubClientBuilder;
import com.azure.messaging.eventhubs.EventHubConsumerAsyncClient;
import com.azure.messaging.eventhubs.models.EventPosition;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class EventHubConsumerLowLevel {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventHubConsumerLowLevel.class);

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

        consumer.getPartitionIds().subscribe(partitionId -> {
            consumer.receiveFromPartition(partitionId, EventPosition.latest())
                    .subscribe(event -> {
                        String body = event.getData().getBodyAsString();
                        LOGGER.info("Received PDF/A message from partition {}: {}", partitionId, body);
                    });
        });
    }
}
