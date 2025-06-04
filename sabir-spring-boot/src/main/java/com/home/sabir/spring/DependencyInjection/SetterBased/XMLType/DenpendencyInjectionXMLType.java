package com.home.sabir.spring.DependencyInjection.SetterBased.XMLType;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DenpendencyInjectionXMLType {
    public static void main(String[] args) {
        
        // Load the Spring configuration file
        ApplicationContext context 
        = new ClassPathXmlApplicationContext("config.xml");

        // Retrieve the bean from the Spring container
        Student student = (Student) context.getBean("stud");

        // Print the student details
        System.out.println(student);
    }
}