package com.home.sabir.spring.DependencyInjection.SetterBased.AnnotationType;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class DependencyInjectionAnnotationType {

    public static void main(String[] args) {
        // Load the Spring application context using annotation-based configuration
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // Retrieve the Student bean
        Student student = context.getBean(Student.class);

        // Print the student details
        System.out.println(student);
    }
}