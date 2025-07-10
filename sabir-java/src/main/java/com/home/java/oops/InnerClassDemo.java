package com.home.java.oops;


class Outer_Demo {

	// inner class
	   private static class Inner_Demo {
	      public void print() {
	         System.out.println("This is an inner class");
	      }
	   }
	   
	   // Accessing he inner class from the method within
	   void display_Inner() {
	      Inner_Demo inner = new Inner_Demo();
	      inner.print();
	   }
	}
	   
	public class InnerClassDemo {

	   public static void main(String[] args) {
	      // Instantiating the outer class 
	      Outer_Demo outer = new Outer_Demo();
	      
	      // Accessing the display_Inner() method.
	      outer.display_Inner();
	   }
	}