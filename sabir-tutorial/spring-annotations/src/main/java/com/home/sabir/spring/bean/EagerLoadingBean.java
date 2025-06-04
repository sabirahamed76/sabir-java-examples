package com.home.sabir.spring.bean;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class EagerLoadingBean {

    public EagerLoadingBean() {

        System.out.println("EagerLoadingBean object created ..");
    }

    @PostConstruct
    public void init() {
        System.out.println("Bean initialized!");
    }



    @PreDestroy
    public void cleanup() {
        System.out.println("Bean is being destroyed!");
    }



    @EventListener(ApplicationReadyEvent.class)
    public void onStartup() {
        System.out.println("Application is ready!");
    }

}
