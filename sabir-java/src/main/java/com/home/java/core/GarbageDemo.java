/*
 * GarbageDemo.java
 *
 * Created on December 11, 2009, 9:33 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.home.java.core;

public class GarbageDemo {
    Garbage g;
    int max;
    public static void main(String[] args) {
        int n = 15;
        if(args.length > 0) n = (Integer.valueOf(args[0]));
        GarbageDemo app = new GarbageDemo(n);
        app.run();
    }
    public GarbageDemo(int n) {
        max = n;
    }
    void run() {
        for(int i=1;i<max;++i) {
            g = new Garbage(i);
        }
    }
}
class Garbage implements AutoCloseable{
    String[] trash;
    int value;
    public Garbage(int n) {
        value = n;
        trash = new String[n];
        trash[0] = "This String uses up memory resources.";
        for(int i=1;i<n;++i)
        trash[i] = trash[i-1] + trash[i-1];
    }
    @Override
    public void close() {
        System.out.println("Resource cleaned up!");
    }

}