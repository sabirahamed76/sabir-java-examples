package com.home.sabir.spring.aop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaSpringBootConfiguration {

    @Bean
    public String someDummyBean1() {
        return "someDummyBean1";
    }

    @Bean
    public String someDummyBean2() {
        return "someDummyBean2";
    }

}