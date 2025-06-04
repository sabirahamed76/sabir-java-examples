package com.home.sabir.spring.DependencyInjection.AnnotationQualifierDI;

import org.springframework.stereotype.Component;

@Component("FortuneServiceQualifier2")
public class RandomFortuneService implements FortuneServiceInterface {

    @Override
    public String getFortune() {
        return "Today is a lucky day!!!";
    }
}
