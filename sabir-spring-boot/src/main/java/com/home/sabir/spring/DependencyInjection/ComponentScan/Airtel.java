package com.home.sabir.spring.DependencyInjection.ComponentScan;

import org.springframework.stereotype.Component;

@Component("airtelBean")
public class Airtel implements Sim {
	@Override
    public void calling() {
        System.out.println("Airtel Calling");
    }

    @Override
    public void data() {
        System.out.println("Airtel Data");
    }
}

