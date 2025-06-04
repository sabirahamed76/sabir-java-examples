package com.home.java.core;


abstract class Shape{
    public abstract void draw();

    public String display(){
         return "I am a shape";
    }
}

class Round extends Shape{
    public void draw(){
        System.out.println("Round is drawing");
    }
}

class Square extends Shape{
    public void draw(){
        System.out.println("Square is drawing");
    }
}


//All methods need to be overridden in implemented class
//Multiple interfaces can be implemented in class multiple inheritance
interface Vehicle1{
    public void run();

    default void display(){
        System.out.println("Vehicle is displaying");
    }
}

class Car implements Vehicle1{
    public void run(){
        System.out.println("Car is running");
    }
}

class Bike implements Vehicle1{
    public void run(){
        System.out.println("Bike is running");
    }
}


public class AbstractAndInterfaceDemo {

    public static void main(String[] args){
        System.out.println("===============================");
        System.out.println("Abstract Class Demo");
        System.out.println("===============================");
        Shape s1= new Round();
        Shape s2 = new Square();
        if (s1 instanceof Round){
            System.out.println(s1.display()+" Round");
        }else if (s1 instanceof Square){
            System.out.println(s1.display()+" Square");
        }
        s1.draw();
        if (s2 instanceof Round){
            System.out.println(s2.display()+" Round");
        }else if (s2 instanceof Square){
            System.out.println(s2.display()+" Square");
        }
        s2.draw();

        System.out.println("===============================");
        System.out.println("Interface Demo");
        System.out.println("===============================");
        Vehicle1 v1= new Car();
        Vehicle1 v2= new Bike();
        v1.run();
        v2.run();

    }

}
