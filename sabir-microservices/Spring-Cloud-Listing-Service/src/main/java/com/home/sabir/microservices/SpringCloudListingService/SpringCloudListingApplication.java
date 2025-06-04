package com.home.sabir.microservices.SpringCloudListingService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class SpringCloudListingApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudListingApplication.class, args);
	}

}
