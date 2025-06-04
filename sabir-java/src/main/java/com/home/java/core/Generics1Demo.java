package com.home.java.core;

abstract class Handler<T> {
	   public T content;

	   public Handler(T content) {
	      this.content = content; 
	   }
	   
	   abstract void handle();
	}
	

	
public class Generics1Demo {
	public static void main(String[] args) {
	      // create an Anonymous class to handle 1
		  // Here we need to pass Type arguments in diamond operator 
		  // before Java 9 otherwise compiler will complain error
	      Handler<Integer> intHandler1 = new Handler<Integer>(1) {
	         @Override
	         public void handle() {
	            System.out.println("The Value is = " +content);
	         }
	      };
	      intHandler1.handle();
	      
	      // create an Anonymous class to handle 2	  
	      Handler<? extends Number> intHandler2 = new Handler<Number>(2) {
	         @Override
	         public void handle() {
	            System.out.println("The Value is = " +content);
	         }
	      };
	      intHandler2.handle();
	      

	      // create an Anonymous class to handle test	      
	      Handler<?> intHandlerTest = new Handler<Object>("test") {
	         @Override
	         public void handle() {
	            System.out.println("The Value is = " +content);
	         }
	      };
	      intHandlerTest.handle();    
	   }  
}
