package com.home.sabir.spring.DependencyInjection.SetterBased.JavaType;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AppConfig {

    @Bean
    public Student student(@Value("Maryam") String studentName, 
                           @Value("Spring Framework") String studentCourse) {
        Student student = new Student();
        student.setStudentName(studentName);
        student.setStudentCourse(studentCourse);
        return student;
    }
}