package com.home.java.logic;


//Fibonacci series program in java

import java.io.*;

class Fibonacci {
 // Function to print N Fibonacci Number
 static void printFibonacci(int N)
 {
     int num1 = 0, num2 = 1;

     for (int i = 0; i < N; i++) {
         // Print the number
         System.out.print(num1 + " ");

         // Swap
         int num3 = num2 + num1;
         num1 = num2;
         num2 = num3;
     }
 }

 // Driver Code
 public static void main(String args[])
 {
     // Given Number N
     int N = 10;

     // Function Call
     printFibonacci(N);
 }
}
