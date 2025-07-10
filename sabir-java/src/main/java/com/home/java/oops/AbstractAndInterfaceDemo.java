package com.home.java.oops;


abstract class Shape2{
    public abstract void draw();

    public String display(){
         return "I am a shape";
    }
}

class Round2 extends Shape2{
    public void draw(){
        System.out.println("Round is drawing");
    }
}

class Square2 extends Shape2{
    public void draw(){
        System.out.println("Square is drawing");
    }
}


//All methods need to be overridden in implemented class
//Multiple interfaces can be implemented in class multiple inheritance
interface Vehicle2{
    public void run();

    default void display(){
        System.out.println("Vehicle is displaying");
    }
}

class Car2 implements Vehicle2{
    public void run(){
        System.out.println("Car is running");
    }
}

class Bike2 implements Vehicle2{
    public void run(){
        System.out.println("Bike is running");
    }
}


public class AbstractAndInterfaceDemo {

    public static void main(String[] args){
        System.out.println("===============================");
        System.out.println("Abstract Class Demo");
        System.out.println("===============================");
        Shape2 s1= new Round2();
        Shape2 s2 = new Square2();
        if (s1 instanceof Round2){
            System.out.println(s1.display()+" Round");
        }else if (s1 instanceof Square2){
            System.out.println(s1.display()+" Square");
        }
        s1.draw();
        if (s2 instanceof Round2){
            System.out.println(s2.display()+" Round");
        }else if (s2 instanceof Square2){
            System.out.println(s2.display()+" Square");
        }
        s2.draw();

        System.out.println("===============================");
        System.out.println("Interface Demo");
        System.out.println("===============================");
        Vehicle2 v1= new Car2();
        Vehicle2 v2= new Bike2();
        v1.run();
        v2.run();

    }

}
