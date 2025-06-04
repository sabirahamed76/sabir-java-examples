package com.home.java.lang;
import java.util.Scanner;

import java.util.Properties;

public class SystemDemo {
	public static void main (String[] args) {
		/* Use of getProperties() method
        System class refers to the JVM on which you are compiling your JAVA code
        getProperty fetches the actual properties
        that JVM on your System gets from your Operating System
    */
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your name: ");
        String name = scanner.nextLine();
        System.out.println("Hello, " + name);


    System.out.println("Following are the JVM information of your OS :");
    System.out.println("");
     
    // Property Object
    Properties jvm = System.getProperties();
    jvm.list(System.out);
    
    // Printing 'name of Operating System'
    System.out.println("os.name: "+System.getProperty("os.name"));
    
    System.out.println("dbType: "+System.getProperty("dbType"));
	}
}
