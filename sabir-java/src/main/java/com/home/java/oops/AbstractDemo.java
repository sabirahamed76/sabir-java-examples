/*
 * Abstract.java
 *
 * Created on November 27, 2008, 3:11 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.home.java.oops;

/**
 *
 * @author siddisab
 */


abstract class Shape {

	abstract public double area();
        
        public double area(Object o){double i=0.0; return i;};
}

class Circle extends Shape {

	static int r;
	public Circle(int r) {
		this.r = r;
	}
	public double area() {
		return 3.14*r*r;
	}
	
}

class Square extends Shape {

	static int a;
	public Square(int a) {
		this.a=a;
	}
	public double area() {
		return a*a;
	}
}


public class AbstractDemo {
    
    /** Creates a new instance of Abstract */
    public AbstractDemo() {
    }
    public static void main(String args[]) {
		Circle p = new Circle(5);
                Square s = new Square(5);
                System.out.println("Area of the Circle="+p.area());
                System.out.println("Area of the Square="+s.area());
	}
}


/*
 *
1. Abstract class is a class which contain one or more abstract methods, which has to be implemented by sub classes. 
 An abstract class can contain no abstract methods also i.e. abstract class may contain concrete methods. 
 A Java Interface can contain only method declarations and public static final constants and doesn't contain their implementation. 
 The classes which implement the Interface must provide the method definition for all the methods present.

2. Abstract class definition begins with the keyword "abstract" keyword followed by Class definition. 
 An Interface definition begins with the keyword "interface".

3. Abstract classes are useful in a situation when some general methods should be implemented and specialization behavior 
 should be implemented by subclasses. Interfaces are useful in a situation when all its properties need to be implemented by subclasses

4. All variables in an Interface are by default - public static final while an abstract class can have instance variables.

5. An interface is also used in situations when a class needs to extend an other class apart from the abstract class. 
 In such situations its not possible to have multiple inheritance of classes. An interface on the other hand can be used 
 when it is required to implement one or more interfaces. Abstract class does not support Multiple Inheritance 
 whereas an Interface supports multiple Inheritance.

6. An Interface can only have public members whereas an abstract class can contain private as well as protected members.

7. A class implementing an interface must implement all of the methods defined in the interface, 
 while a class extending an abstract class need not implement any of the methods defined in the abstract class.

8. The problem with an interface is, if you want to add a new feature (method) in its contract, 
 then you MUST implement those method in all of the classes which implement that interface. 
 However, in the case of an abstract class, the method can be simply implemented in the abstract class 
 and the same can be called by its subclass

9. Interfaces are slow as it requires extra indirection to to find corresponding method in in the actual class. 
 Abstract classes are fast

10.Interfaces are often used to describe the peripheral abilities of a class, and not its central identity, 
 E.g. an Automobile class might implement the Recyclable interface, which could apply to many otherwise totally unrelated objects.
 *
 */