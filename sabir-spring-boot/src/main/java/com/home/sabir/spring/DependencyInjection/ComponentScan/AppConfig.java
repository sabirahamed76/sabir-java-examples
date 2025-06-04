package com.home.sabir.spring.DependencyInjection.ComponentScan;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.home.sabir.spring.DependencyInjection")
public class AppConfig {

    @Bean
    public Sim jio() {
        return new Jio(); 
    }
    
    @Bean
    public Sim airtel() {
        return new Airtel(); 
    }
}