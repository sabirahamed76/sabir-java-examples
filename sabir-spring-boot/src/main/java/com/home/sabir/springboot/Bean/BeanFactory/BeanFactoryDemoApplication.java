package com.home.sabir.springboot.Bean.BeanFactory;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
//Main class
public class BeanFactoryDemoApplication 
{
//Main driver method
public static void main(String[] args) 
{
 // Creating object in a spring container (Beans)
 BeanFactory factory = new ClassPathXmlApplicationContext("/bean-factory-demo.xml");
 Student student = (Student) factory.getBean("student");

 System.out.println(student);
}
}