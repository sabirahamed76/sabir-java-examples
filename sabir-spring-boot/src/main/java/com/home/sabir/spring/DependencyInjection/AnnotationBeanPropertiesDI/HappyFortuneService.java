package com.home.sabir.spring.DependencyInjection.AnnotationBeanPropertiesDI;

import org.springframework.stereotype.Component;

@Component("FortuneServiceConfig")
public class HappyFortuneService implements FortuneServiceInterface {

    @Override
    public String getFortune() {
        return "Today is a lucky day!";
    }
}
