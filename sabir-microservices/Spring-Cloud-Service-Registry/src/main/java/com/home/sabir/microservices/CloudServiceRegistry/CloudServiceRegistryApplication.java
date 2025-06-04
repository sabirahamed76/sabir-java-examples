package com.home.sabir.microservices.CloudServiceRegistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class CloudServiceRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudServiceRegistryApplication.class, args);
	}

}
