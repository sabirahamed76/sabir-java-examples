package com.home.java.lang.Wrapper;

public class DoubleDemo {
	   public static void main(String[] args) {

	      // create a String s and assign value to it
	      String s = "+120";

	      // create a Double object b
	      Double b;

	      // get the value of double from string
	      b = Double.valueOf(s);

	      // print b value
	      System.out.println( "Double value of string " + s + " is " + b );
	   }
	}