package com.home.sabir.springboot.SpringData;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class SpringDataJpaDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJpaDemoApplication.class, args);
	}


}
