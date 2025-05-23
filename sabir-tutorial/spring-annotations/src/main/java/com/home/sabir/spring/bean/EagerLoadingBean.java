package com.home.sabir.spring.bean;

import org.springframework.stereotype.Component;

@Component
public class EagerLoadingBean {

    public EagerLoadingBean() {
        System.out.println("EagerLoadingBean object created ..");
    }
}
