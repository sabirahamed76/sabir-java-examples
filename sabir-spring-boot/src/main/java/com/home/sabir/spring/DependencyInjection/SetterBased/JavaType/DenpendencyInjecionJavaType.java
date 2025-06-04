package com.home.sabir.spring.DependencyInjection.SetterBased.JavaType;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class DenpendencyInjecionJavaType {
    public static void main(String[] args) {
        
        // Load the Spring application context 
        // using Java-based configuration
        ApplicationContext context 
        = new AnnotationConfigApplicationContext(AppConfig.class);

        // Retrieve the Student bean
        Student student = context.getBean(Student.class);

        // Print the student details
        System.out.println(student);
    }
}