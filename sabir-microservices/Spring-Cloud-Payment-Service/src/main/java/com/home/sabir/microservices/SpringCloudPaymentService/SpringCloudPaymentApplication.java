package com.home.sabir.microservices.SpringCloudPaymentService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
@EnableDiscoveryClient
@SpringBootApplication
public class SpringCloudPaymentApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudPaymentApplication.class, args);
	}

}
