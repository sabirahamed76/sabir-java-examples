package com.home.sabir.spring.DependencyInjection.ConstructorBased;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DependencyInjectionConstructor {

    public static void main(String[] args) {

        // Load the Spring application context
        ApplicationContext context 
        = new ClassPathXmlApplicationContext("beans.xml");

        // Retrieve the Student bean
        Student student = context.getBean("student", Student.class);

        // Call the Studying() method
        student.studying();
    }
}