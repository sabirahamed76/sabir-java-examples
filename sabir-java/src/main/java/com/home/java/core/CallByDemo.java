/*
 * CallBy.java
 *
 * Created on November 24, 2008, 5:23 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.home.java.core;

/**
 *
 * @author siddisab
 */
class Sample {
    int a;
    int b;
    
    public void setByValue(int a,int b){
        a=a;
        b=b;
    }

    public void setByReference(int a, int b){
        this.a=a;
        this.b=b;
    }


    public int getA() {
        a++;
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        b--;
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

}
public class CallByDemo {
    public static void main(String args[]) {
        int a = 10, b = 20;
        Sample ob = new Sample();

        System.out.println("-----Set By Value-----");
        System.out.println("a and b before call: " + a + " " + b);
        ob.setByValue(a,b);
        System.out.println("a and b after call: " + ob.getA() + " " + ob.getB());

        System.out.println("-----Set By Reference-----");
        System.out.println("a and b before call: " + a + " " + b);
        ob.setByReference(a,b);
        System.out.println("a and b after call: " + ob.getA() + " " + ob.getB());


    }
}
