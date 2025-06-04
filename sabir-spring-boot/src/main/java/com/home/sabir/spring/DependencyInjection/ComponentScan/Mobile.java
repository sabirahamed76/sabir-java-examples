package com.home.sabir.spring.DependencyInjection.ComponentScan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Mobile {

    @Autowired
    @Qualifier("jioBean") // Use the explicit bean name
    private Sim jio;
    
    @Autowired
    @Qualifier("airtelBean") // Use the explicit bean name
    private Sim airtel;

    public void useJio() {
        jio.calling();
        jio.data();
    }
    
    public void useAirtel() {
    	airtel.calling();
    	airtel.data();
    }

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Mobile mobile = context.getBean(Mobile.class);
        mobile.useJio();
        System.out.println("==============");
        mobile.useAirtel();
    }
}