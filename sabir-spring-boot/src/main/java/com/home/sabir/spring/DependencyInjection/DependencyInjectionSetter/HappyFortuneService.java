package com.home.sabir.spring.DependencyInjection.DependencyInjectionSetter;

public class HappyFortuneService implements FortuneServiceInterface {

    @Override
    public String getFortune() {
        return "Today is a lucky day!";
    }
}
