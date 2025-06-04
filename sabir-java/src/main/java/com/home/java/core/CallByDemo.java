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
class CallByValue {
    int a;
    int b;
    
    CallByValue(int a,int b){
        a=a;
        b=b;
    }
   
    void meth() {
        a++;
        b--;
    }
}   
class CallByReference {
    int a;
    int b;
    
    CallByReference(int i,int j){
        this.a=i;
        this.b=j;
    }
    
    void meth() {
        a++;
        b--;
    }
} 
public class CallByDemo {
    public static void main(String args[]) {
        int a = 10, b = 20;
        
        System.out.println("-----Call By Value-----");
        CallByValue ob = new CallByValue(a,b);
        System.out.println("a and b before call: " + a + " " + b);
        ob.meth();
        System.out.println("a and b after call: " + ob.a + " " + ob.b);
        
        System.out.println("-----Call By Reference-----");
        CallByReference ob1 = new CallByReference(a,b);
        System.out.println("a and b before call: " + a + " " + b);
        ob1.meth();
        System.out.println("a and b after call: " + ob1.a + " " + ob1.b);
    }
}
