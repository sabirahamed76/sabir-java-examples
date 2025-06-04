package com.home.java.lang.String;

public class StringBufferDemo {
	public static void main(String args[])
    {
		
		
        StringBuffer sb = new StringBuffer();
      
      	// default 16
        System.out.println(sb.capacity()); 
        sb.append("Hello");
      
      	// now 16
        System.out.println(sb.capacity()); 
        sb.append("java is my favourite language");
        
      	// (oldcapacity*2)+2
      	System.out.println(sb.capacity());
      	
      	StringBuffer sb1 = new StringBuffer("Hello");
        sb1.reverse();
        System.out.println(sb1);
        
        
        StringBuffer sb2 = new StringBuffer("Hello");
        sb2.delete(1, 3);
        System.out.println(sb2);
        
        StringBuffer sb3 = new StringBuffer("Hello");
        sb3.replace(1, 3, "Java");
        System.out.println(sb3);
        
        
        StringBuffer sb4 = new StringBuffer("Hello ");
        sb4.insert(1, "Java");
        System.out.println(sb4);
        
        StringBuffer sb5 = new StringBuffer("Hello ");
        sb5.append("Java"); // now original string is changed
        System.out.println(sb5);
        
        
        
    }
}
