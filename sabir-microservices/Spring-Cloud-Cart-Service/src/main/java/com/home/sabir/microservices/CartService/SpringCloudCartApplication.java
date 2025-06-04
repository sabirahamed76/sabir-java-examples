package com.home.sabir.microservices.CartService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringCloudCartApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudCartApplication.class, args);
	}

}
