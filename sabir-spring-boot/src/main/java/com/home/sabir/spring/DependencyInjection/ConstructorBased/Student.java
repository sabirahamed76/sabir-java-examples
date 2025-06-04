package com.home.sabir.spring.DependencyInjection.ConstructorBased;

public class Student {

    // Class data members
    private int id;
    private MathStudy mathStudy;

    // Constructor for Dependency Injection
    public Student(int id, MathStudy mathStudy) {
        this.id = id;
        this.mathStudy = mathStudy;
    }

    // Method to demonstrate functionality
    public void studying() {
        System.out.println("My ID is: " + id);
        mathStudy.mathStudying();
    }
}