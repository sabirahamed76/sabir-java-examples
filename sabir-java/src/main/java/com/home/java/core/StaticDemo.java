package com.home.java.core;

/*
Use the static variable for the property that is common to all objects. 
*/
class Outer {
	static int a = 10;
    static int b;
    
   //First static block
    static{
        System.out.println("Static Block 1");
    } 
    //Second static block
    static{
        System.out.println("Static Block 2");
    }
    // Third static block
    static {
    	b = a * 4;
        System.out.println("Static outer block initialized.");        
    }
    

    
   static class Inner {
	   static {
		   //b = a * 2;
	        System.out.println("Static inner block initialized.");
	        
	    }
      public static void print() {
         System.out.println("This is static Inner class."+b);
      }
   }
   
   public static void print() {
       System.out.println("This is static Outer class."+b);
    }
   
}
	
public class StaticDemo {

	public static void main(String[] args) {
		System.out.println("from main");
		Outer.Inner.print();
		Outer.print();
	}



}
