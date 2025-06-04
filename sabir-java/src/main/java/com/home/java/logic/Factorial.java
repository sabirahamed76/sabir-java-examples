/*
 * Recursion.java
 *
 * Created on November 24, 2008, 5:18 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.home.java.logic;
/**
 *
 * @author siddisab
 */
public class Factorial {
    
    int fact(int n) {
        int result;
        if(n==1) return 1;
        result = fact(n-1) * n;
        return result;
    }
    
    public static void main(String args[]) {
        Factorial r = new Factorial();
        System.out.println("Factorial of 3 is " + r.fact(3));
        System.out.println("Factorial of 4 is " + r.fact(4));
        System.out.println("Factorial of 5 is " + r.fact(5));
    }
}
