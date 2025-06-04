/*
 * Override.java
 *
 * Created on November 24, 2008, 5:41 PM
 *
 * This is the example for Inheritance
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.home.java.oops;

/**
 *
 * @author siddisab
 * Java program to demonstrate
 * method overriding in java
 */

// Base Class
class Parent {
	String className = "PARENT";
    void show() { 
    	System.out.println("Hello!! I am a " + this.className); 
    }
	String display() {
		return "I love my "+this.className;	
	}
}
 
// Inherited class
class Child extends Parent {
	String className = "CHILD";
	
	
    // This method overrides show() of Parent
    void show(){
        System.out.println("Hi!! I am a " + this.className + " of my " + super.className);
    }
    // This method overrides display() of Parent
	String display() {
		return "Surely ..." + super.display() + " too" ;	
	}
}
 
// Driver class
public class OverrideDemo {
    public static void main(String[] args)
    {
        // If a Parent type reference refers
        // to a Parent object, then Parent's
        // show is called
        Parent obj1 = new Parent();
        obj1.show();
        System.out.println(obj1.display());
 

        System.out.println("===========================================================================");
        // If a Parent type reference refers
        // to a Child object Child's show()
        // is called. This is called RUN TIME
        // POLYMORPHISM.
        Parent obj2 = new Child();
        obj2.show();
        System.out.println(obj2.display());
    }
}
