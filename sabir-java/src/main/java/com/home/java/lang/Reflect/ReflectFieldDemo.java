package com.home.java.lang.Reflect;

import java.lang.reflect.Field;

public class ReflectFieldDemo {  
    
    public static void main(String[] args)  
        throws Exception  
    {  
    
        // Create the User class object  
        User user = new User();  
    
        // Get the all field objects of User class  
        Field[] fields = User.class.getFields();  
    
        for (int i = 0; i < fields.length; i++) {  
    
            // get value of the fields  
            Object value = fields[i].get(user);  
    
            // print result  
            System.out.println("Value of Field "
                               + fields[i].getName()  
                               + " is " + value);  
        }  
    }  
}  
    
// sample User class  
class User {  
    
    public static String name = "Dipsundar";  
    
    public static String getName()  
    {  
        return name;  
    }  
    
    public static void setName(String name)  
    {  
        User.name = name;  
    }  
}