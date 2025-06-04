/**
 * 
 */
package com.home.java.core;

import java.util.Scanner;

/**
 * @author admin
 *
 */

public class ScannerDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// creating the instance of class Scanner
        Scanner obj = new Scanner(System.in);
        String name;
        int rollno;
        float marks;

        System.out.println("Enter your name");

        // taking string input
        name = obj.nextLine();
        System.out.println("Enter your rollno");

        // taking integer input
        rollno = obj.nextInt();
        System.out.println("Enter your marks");

        // taking float input
        marks = obj.nextFloat();
      
        // printing the output
        System.out.println("Name=" + name);
        System.out.println("Rollno=" + rollno);
        System.out.println("Marks=" + marks);

	}

}
