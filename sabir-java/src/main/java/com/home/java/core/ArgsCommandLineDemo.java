package com.home.java.core;

public class ArgsCommandLineDemo {

	public static void main(String[] args) {
		// if only one argument is passed
	      if(args.length == 1) {
		     String name = args[0];
	         System.out.println("Welcome " + name + "!");
	      }else { // otherwise print an error message
	         System.out.println("Invalid Command line argument(s) passed.");        
	      }	  
	}

}
