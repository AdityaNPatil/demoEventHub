package com.example.demoEventHub.pdfa;

import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.messaging.eventhubs.EventData;
import com.azure.messaging.eventhubs.EventDataBatch;
import com.azure.messaging.eventhubs.EventHubClientBuilder;
import com.azure.messaging.eventhubs.EventHubProducerClient;
import com.azure.messaging.eventhubs.models.CreateBatchOptions;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/publish")
public class EventPublisher {
     private String namespace = "pdfa-dev-namespace";
     private String eventHubName = "pdfa-eventhub";

        @PostMapping
        public String publish(@RequestBody PdfaEvent dto) {
            String fullyQualifiedNamespace = namespace + ".servicebus.windows.net";

            EventHubProducerClient producer = new EventHubClientBuilder()
                    .credential(fullyQualifiedNamespace, eventHubName, new DefaultAzureCredentialBuilder().managedIdentityClientId("a2e0e349-9b2c-44fb-909b-4fe1a4685588").build())
                    .buildProducerClient();

            EventDataBatch batch = producer.createBatch(new CreateBatchOptions());
            batch.tryAdd(new EventData(dto.toString()));
            producer.send(batch);
            producer.close();

            return "Message sent!";
        }
    }
