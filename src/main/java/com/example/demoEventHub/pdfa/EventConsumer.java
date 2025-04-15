package com.example.demoEventHub.pdfa;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import java.util.function.Consumer;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class EventConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(EventConsumer.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Bean
    public Consumer<Message<String>> consume() {
        return message -> {
            try {
                PdfaEvent event = objectMapper.readValue(message.getPayload(), PdfaEvent.class);
                LOGGER.info("Received EventHub Message: JobId={}, Status={}, CustomerId={}", event.getConversionJobId(), event.getStatus(), event.getCustomerId());
            } catch (Exception e) {
                LOGGER.error("Error processing message", e);
            }
        };
    }
}
