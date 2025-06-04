package com.home.java.lang.Wrapper;

public class LongDemo {

	   public static void main(String[] args) {

	      // create a String s and assign value to it
	      String s = "+120";

	      // create a Long object l
	      Long l;

	      // get the value of long from string
	      l = Long.valueOf(s);

	      // print the value
	      System.out.println( "Long value of string " + s + " is " + l );
	   }
	}