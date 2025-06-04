package com.home.java.core;


class A{
    Integer i=1;

    public Integer display(){
         return this.i;
    }
}

class B extends A{
    Integer i=2;

    public Integer display(){
        System.out.println("Display Parent value" + super.display());
        return this.i;

    }
}
public class ThisAndSuperDemo {

    public static void main(String[] args){
        B b = new B();
        System.out.println("Display Child value" + b.display());
    }

}
