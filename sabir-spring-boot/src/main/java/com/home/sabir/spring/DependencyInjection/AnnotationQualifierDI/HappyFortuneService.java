package com.home.sabir.spring.DependencyInjection.AnnotationQualifierDI;

import org.springframework.stereotype.Component;

@Component("FortuneServiceQualifier1")
public class HappyFortuneService implements FortuneServiceInterface {

    @Override
    public String getFortune() {
        return "Today is a lucky day!";
    }
}
