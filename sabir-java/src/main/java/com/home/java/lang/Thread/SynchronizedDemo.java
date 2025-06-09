package com.home.java.lang.Thread;

/**
 * @author sabeer
 */

// Java Program to Illustrate Thread Safe And
// Synchronized Programs as of Complete Iterations
// using 'synchronized' Keyword

// Importing input output classes
import java.io.*;

// Class 1
// helper Class
class TickTock {

    // Member variable of this class
    int count;

    // Method of this Class
    public synchronized void increment()
    {

        // Increment count by unity
        count++;
    }
    //
}

// Class 2
// Synchronization demo class
// Main Class
class SynchronizedDemo {

    // Main driver method
    public static void main(String[] args) throws Exception
    {

        // Creating an object of class TickTock in main()
        TickTock tt = new TickTock();

        // Now, creating an thread object
        // using Runnable interface
        Thread t1 = new Thread(new Runnable() {
            // Method
            // To begin the execution of thread
            public void run()
            {

                // Expression
                for (int i = 0; i < 100000; i++) {

                    // Calling object of above class
                    // in main() method
                    tt.increment();
                }
            }
        });

        // Thread 2
        Thread t2 = new Thread(new Runnable() {
            // Method
            // To begin the execution of thread
            public void run()
            {

                // Expression
                for (int i = 0; i < 100000; i++) {

                    // Calling object of above class
                    // in main() method
                    tt.increment();
                }
            }
        });

        // Making above thread created to start
        // via start() method which automatically
        // calls run() method
        t1.start();
        t2.start();

        // Now we are making main() thread to wait so
        // that thread t1 completes it job
        t1.join();
        t2.join();

        // Print and display the count
        System.out.println("Count : " + tt.count);
    }
}