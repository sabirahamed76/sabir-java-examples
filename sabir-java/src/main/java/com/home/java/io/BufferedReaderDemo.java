package com.home.java.io;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BufferedReaderDemo {

	public static void main(String[] args) {
		// creating the instance of class BufferedReader
        BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));
        String name;
        try {
            System.out.println("Enter your name");
            name = reader.readLine(); // taking string input
            System.out.println("Name=" + name);
        }
        catch (Exception e) {
        }

	}

}
