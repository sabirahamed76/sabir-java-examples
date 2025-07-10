/*
 * Static.java
 *
 * Created on November 27, 2008, 4:34 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.home.java.oops;

/**
 *
 * @author siddisab
 */

/*
Use the static variable for the property that is common to all objects.
*/
class Outer {
    static int a = 10;
    static int b;

    //First static block
    static{
        System.out.println("Static Block 1");
    }
    //Second static block
    static{
        System.out.println("Static Block 2");
    }
    // Third static block
    static {
        b = a * 4;
        System.out.println("Static outer block initialized.");
    }



    static class Inner {
        static {
            //b = a * 2;
            System.out.println("Static inner block initialized.");

        }
        public static void print() {
            System.out.println("This is static Inner class."+b);
        }
    }

    public static void print() {
        System.out.println("This is static Outer class."+b);
    }

}


public class StaticDemo {
    
    //This variable can be accessed anywhere
    public static int i;
    public int j=10;
    
    //This block executed automatically
    static{
        i=10;
    }
    
    //This method can only access static variables
    public static int getI(){
        return ++i;
    }
    
    public int getJ(){
        ++i;
        return ++j;
    }
    
    public static void main(String args[]){
        System.out.println("The Value of I is = " + StaticDemo.i);
        
        StaticDemo s=new StaticDemo();
        System.out.println("The Value of J is = " + s.getJ());

        Outer.Inner.print();
        Outer.print();
    }
    
}
