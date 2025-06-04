package com.home.java.lang.Reflect;

import java.lang.reflect.Method;

public class ReflectMethodDemo { 
	  
    public static void main(String[] args) 
    { 
  
        Method[] methods = Demo.class.getMethods(); 
  
        // calling method getDefaultValue() 
        System.out.println(methods[0].getDefaultValue()); 
        System.out.println(methods[1].toString()); 
        
    } 
} 



class Demo { 
    private String str; 
  
    // member function returning string str 
    public String getSampleField() { return str; } 
  
    public void setSampleField(String str) 
    { 
        this.str = str; 
    } 
}

