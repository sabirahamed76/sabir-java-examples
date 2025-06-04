package com.home.sabir.spring.caching.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan("com.home.sabir.spring.*")
public class AppConfig {
  
}