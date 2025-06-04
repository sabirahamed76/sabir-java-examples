package com.home.sabir.springboot.SpringAOP;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class AopExampleApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(AopExampleApplication.class, args);
        UserService userService = context.getBean(UserService.class);
        
        userService.createUser();
        try {
            userService.throwError();
        } catch (Exception e) {
            System.out.println("Handled exception: " + e.getMessage());
        }
    }
}
