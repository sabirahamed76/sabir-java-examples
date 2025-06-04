package com.home.java.lang.Reflect;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

class Calculate {
	 
    // Function 1
    // To add to numbers
    int add(int a, int b)
    {
 
        // Returning the sum
        return (a + b);
    }
 
    // Function 2
    // To multiply two numbers
    int mul(int a, int b)
    {
 
        // Returning the number obtained
        // after multiplying numbers
        return (b * a);
    }
 
    // Function 3
    // Subtracting two numbers
    long subtract(long a, long b)
    {
        // Return the numbers after subtracting
        // second number from the first number
        return (a - b);
    }
}
 
// Class 2
// Main class
public class ReflectDemo {
 
    // Main driver method
    public static void main(String[] args)
    {
        // Creating object of helper class
        // in the main method
        Class cls = Calculate.class;
 
        // Getting all the methods of
        // a particular class
        Method[] methods = cls.getDeclaredMethods();
 
        // Iterating over each method
        // using the for each loop
        for (Method method : methods) {
 
            // Print and display the method name
            // using getName() method
            System.out.print(
                "Method Name: " + method.getName() + " ");
 
            // Getting all the parameters of
            // a particular method
            Parameter parameters[] = method.getParameters();
 
            // Print and display
            System.out.println("\nparameters of "
                               + method.getName()
                               + "() methods: ");
 
            // Iterating over parameters
            // using for each loop
            for (Parameter parameter : parameters) {
 
                // Display the type of parameters
                System.out.print(
                    parameter.getParameterizedType() + " ");
 
                // Print the parameters of method
                System.out.print(parameter.getName() + " ");
            }
 
            // New line
            System.out.print("\n\n");
        }
    }
}