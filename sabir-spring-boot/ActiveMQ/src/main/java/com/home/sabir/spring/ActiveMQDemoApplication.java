package com.home.sabir.spring;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.home.sabir.spring.*")
public class ActiveMQDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ActiveMQDemoApplication.class, args);
	}

}
