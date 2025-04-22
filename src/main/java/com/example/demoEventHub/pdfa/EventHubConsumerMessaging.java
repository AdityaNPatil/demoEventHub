package com.example.demoEventHub.pdfa;

import com.azure.spring.messaging.eventhubs.implementation.core.annotation.EventHubsListener;
import org.springframework.stereotype.Service;

@Service
public class EventHubConsumerMessaging {
    private static final String EVENT_HUB_NAME = "pdfa-eventhub";
    private static final String CONSUMER_GROUP = "$DEFAULT";

    @EventHubsListener(destination = EVENT_HUB_NAME, group = CONSUMER_GROUP)
    public void handleMessageFromEventHub(String message) {
        System.out.printf("New PDF/A EventHub message received: %s%n", message);
    }

}
