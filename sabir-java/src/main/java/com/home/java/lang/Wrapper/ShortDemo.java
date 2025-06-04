package com.home.java.lang.Wrapper;

public class ShortDemo {

	   public static void main(String[] args) {

	      // create a String s and assign value to it
	      String s = "+120";

	      // create a Short object i
	      Short i;

	      // get the value of short from string
	      i = Short.valueOf(s);

	      // print the value
	      System.out.println( "Short value of string " + s + " is " + i );
	   }
	}