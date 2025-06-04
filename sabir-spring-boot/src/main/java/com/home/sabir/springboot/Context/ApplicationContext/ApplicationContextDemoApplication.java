package com.home.sabir.springboot.Context.ApplicationContext;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


@SpringBootApplication
public class ApplicationContextDemoApplication {

  // Main driver method
  public static void main(String[] args) {


	  ApplicationContext context = SpringApplication.run(ApplicationContextDemoApplication.class, args);

	  Student student = context.getBean(Student.class);

	  // Print and display
	  System.out.println(student);
	  
  }
}