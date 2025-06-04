package com.home.sabir.spring.DependencyInjection.AnnotationFieldDI;

import org.springframework.stereotype.Component;

@Component("FortuneServiceField")
public class HappyFortuneService implements FortuneServiceInterface {

    @Override
    public String getFortune() {
        return "Today is a lucky day!";
    }
}
