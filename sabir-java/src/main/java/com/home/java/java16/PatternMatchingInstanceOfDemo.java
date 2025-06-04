package com.home.java.java16;

public class PatternMatchingInstanceOfDemo {

	    public static void main(String[] args) {
	        Object obj = "Hello, Java 16!";

	        if (obj instanceof String s) { // Pattern matching in action
	            System.out.println("String length: " + s.length());
	        } else {
	            System.out.println("Not a String");
	        }

	}

}
