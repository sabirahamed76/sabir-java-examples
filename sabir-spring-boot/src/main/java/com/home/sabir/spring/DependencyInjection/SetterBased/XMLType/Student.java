package com.home.sabir.spring.DependencyInjection.SetterBased.XMLType;

public class Student {
    private String studentName;
    private String studentCourse;

    // No-argument constructor (required for Setter-Based DI)
    public Student() {
    }

    // Setter methods for Dependency Injection
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setStudentCourse(String studentCourse) {
        this.studentCourse = studentCourse;
    }

    @Override
    public String toString() {
        return "Student{studentName=" + studentName 
        + ", studentCourse=" + studentCourse + "}";
    }
}