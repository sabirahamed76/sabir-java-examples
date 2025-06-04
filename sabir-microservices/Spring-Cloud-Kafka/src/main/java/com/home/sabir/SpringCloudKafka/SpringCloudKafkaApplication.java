package com.home.sabir.SpringCloudKafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class SpringCloudKafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudKafkaApplication.class, args);
	}

}
