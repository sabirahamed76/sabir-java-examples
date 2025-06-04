package com.home.java.core;


//Method Name and Same as Class Name
//No Return Type
//Automatic Call when new object created
public class ConstructorDemo {
    String name=null;
    public ConstructorDemo(){
        name="JAVA Constructor with out param";
    }

    public ConstructorDemo(String name){
        this.name=name;
    }
    
    public static void main(String[] args){
        ConstructorDemo cd = new ConstructorDemo();
        System.out.println(cd.name );
        ConstructorDemo cd1 = new ConstructorDemo("JAVA Constructor with param");
        System.out.println(cd1.name );
    }
}
