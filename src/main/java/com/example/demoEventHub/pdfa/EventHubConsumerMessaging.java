//package com.example.demoEventHub.pdfa;
//
//import com.azure.spring.messaging.eventhubs.implementation.core.annotation.EventHubsListener;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//@Service
//public class EventHubConsumerMessaging {
//    @Value("${pdfa.eventhub.name}")
//    private final String eventHubName;
//    @Value("${pdfa.eventhub.consumergroup}")
//    private final String consumerGroup;
//
//    private static final String EVENT_HUB_NAME = "pdfa-eventhub";
//    private static final String CONSUMER_GROUP = "$DEFAULT";
//
//    @EventHubsListener(destination = eventHubName, group = CONSUMER_GROUP)
//    public void handleMessageFromEventHub(String message) {
//        System.out.printf("New PDF/A EventHub message received: %s%n", message);
//    }
//
//}
