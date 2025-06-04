package com.home.sabir.spring.DependencyInjection.ComponentScan;

import org.springframework.stereotype.Component;

@Component("jioBean")
public class Jio implements Sim {
	@Override
    public void calling() {
        System.out.println("Jio Calling");
    }

    @Override
    public void data() {
        System.out.println("Jio Data");
    }
}