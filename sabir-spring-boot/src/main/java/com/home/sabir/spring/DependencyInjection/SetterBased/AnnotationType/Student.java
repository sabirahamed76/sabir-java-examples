package com.home.sabir.spring.DependencyInjection.SetterBased.AnnotationType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Student {
	private String studentName;
    private String studentCourse;

    @Autowired
    public void setStudentName(@Value("Yahyaa") String studentName) {
        this.studentName = studentName;
    }

    @Autowired
    public void setStudentCourse(@Value("Spring Framework") String studentCourse) {
        this.studentCourse = studentCourse;
    }

    @Override
    public String toString() {
        return "Student{studentName=" + studentName + ", studentCourse=" + studentCourse + "}";
    }
}