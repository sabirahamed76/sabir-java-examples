/*
 * Generics.java
 *
 * Created on December 12, 2009, 7:49 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.home.java.core;

class Gen<T> {   
  T ob;  
     
  Gen(T o) {   
    ob = o;   
  }   
   
  // Return ob.   
  T getob() {   
    return ob;   
  }   
}   
  
// A subclass of Gen.  
class subGen<T> extends Gen<T> {  
	subGen(T o) {  
	    super(o);  
	  }  
}  
  
 // Demonstrate runtime type ID implications of generic class hierarchy.  
public class GenericsDemo {   
  public static void main(String args[]) {   
     
    // Create a Gen object for Integers.  
    Gen<Integer> iOb = new Gen<Integer>(88);  
  
    // Create a subGen object for Integers.  
    subGen<Integer> iOb2 = new subGen<Integer>(99);   
    
    // Create a subGen object for Strings.  
    subGen<String> strOb2 = new subGen<String>("Generics Test");   
  
    // See if iOb2 is some form of subGen. 
    if(iOb2 instanceof subGen<?>)   
      System.out.println("iOb2 is instance of subGen");  
 
    // See if iOb2 is some form of Gen. 
    if(iOb2 instanceof Gen<?>)   
      System.out.println("iOb2 is instance of Gen");  
  
    System.out.println();  
  
    // See if strOb2 is a subGen. 
    if(strOb2 instanceof subGen<?>)   
      System.out.println("strOb is instance of subGen");  
  
    // See if strOb2 is a Gen. 
    if(strOb2 instanceof Gen<?>)   
      System.out.println("strOb is instance of Gen");  
 
    System.out.println();  
  
    // See if iOb is an instance of subGen, which its not. 
    if(iOb instanceof subGen<?>)   
      System.out.println("iOb is instance of subGen");  
  
    // See if iOb is an instance of Gen, which it is. 
    if(iOb instanceof Gen<?>)   
      System.out.println("iOb is instance of Gen");  
  
    // The following can't be compiled because  
    // generic type info does not exist at runtime. 
    /*
     * 
     * if(iOb2 instanceof subGen<Integer>)   
     * System.out.println("iOb2 is instance of subGen<Integer>");  
     */
    }   
}
