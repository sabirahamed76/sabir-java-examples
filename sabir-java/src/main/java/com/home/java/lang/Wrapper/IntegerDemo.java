package com.home.java.lang.Wrapper;

public class IntegerDemo {

	   public static void main(String[] args) {

	      // create a String s and assign value to it
	      String str = "+120";

	      // create a Integer object i
	      Integer intObject;

	      // get the value of int from string
		   intObject = Integer.valueOf(str);

		  //Convert into primitive type
		   int i = intObject.intValue();

	      // print the value
	      System.out.println( "Integer value of string " + str + " is " + intObject );
	   }
	}