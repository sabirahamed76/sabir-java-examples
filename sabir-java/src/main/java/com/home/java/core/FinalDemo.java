/*
 * Final.java
 *
 * Created on November 27, 2008, 4:40 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.home.java.core;

/**
 *
 * @author siddisab
 */

class A1{
   //This variable cannot be changed anywhere
    public final int i=10;
    
    //This method cannot be overridden to any other sub classes
    public final int getI(){
        return i;
    }
}

//This class cannot be extended to any other class
public final class FinalDemo extends A1{
    public int i;
    
    //Can Overload but cannot Override because it is final method in base class
    public int getI(int j){
        i=j;
        ++i;
        return i;
    }
    
    public static void main (String args []){
        FinalDemo f=new FinalDemo();
        System.out.println("The final variable cannot be modified and the value is = " + f.getI());
                
        System.out.println("The instance variable can be modified and the value is = " + f.getI(10));
    }
    
}
