package com.example.demoEventHub;

import com.azure.spring.messaging.implementation.annotation.EnableAzureMessaging;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAzureMessaging
public class DemoEventHubApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoEventHubApplication.class, args);
	}

}
