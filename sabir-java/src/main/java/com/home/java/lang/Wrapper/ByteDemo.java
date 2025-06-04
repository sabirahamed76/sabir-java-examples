package com.home.java.lang.Wrapper;

public class ByteDemo {

	   public static void main(String[] args) {

	      // create a String s and assign value to it
	      String s = "+120";

	      // create a Byte object b
	      Byte b;

	      // get the value of byte from string
	      b = Byte.valueOf(s);

	      // print the value
	      System.out.println( "Byte value of string " + s + " is " + b );
	   }
	}