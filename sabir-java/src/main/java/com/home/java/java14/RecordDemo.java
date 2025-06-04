package com.home.java.java14;

import com.google.errorprone.annotations.Immutable;

//Immutable by default (fields are final).
//Less boilerplate compared to traditional POJOs.
//Better readability for data-centric classes.
record Person(String name, int age) {

    static int perToken;

    public String greet() {
        return "Hello, my name is " + name;
    }

    // Static methods
    public static int generatePersonToken() {
        return ++perToken;
    }

}

public class RecordDemo {

    public static void main(String[] args)     {
        Person person = new Person("Alice", 25);
        System.out.println(person.name()); // Output: Alice
        System.out.println(person.age());  // Output: 25
        System.out.println(person.generatePersonToken());

    }



}
