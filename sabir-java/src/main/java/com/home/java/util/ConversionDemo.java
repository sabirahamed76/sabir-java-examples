package com.home.java.util;

import java.util.Arrays;
import java.util.List;

public class ConversionDemo {

	
	public List covertListFromArray(String theArray[]) {
		            
        // converting array into Collection
        // with asList() function
        List theList = Arrays.asList(theArray);
        return theList;
     }
	
	public String[] covertArrayFromList(List<String> lst) {
        
        // converting with toArray() function
        String[] str = lst.toArray(new String[0]);
        return str;
     }
	
	
	public static void main(String[] args) {
		// array input
		ConversionDemo cd =new ConversionDemo();
        String myArray[] = { "I", "LOVE", "ALLAH" , "MUHAMMED", "ISLAM", "QURAN" , "MASJID"   };

        // printing input elements for comparison
        System.out.println("Array input: "
                           + Arrays.toString(myArray));
        
		// print List Convert From Array
        System.out.println();
        List lst=cd.covertListFromArray(myArray);
        System.out.println("Converted List from Array: "
                           + lst);
        
        
        //print Array converted from List
        System.out.println();
        String[] str = cd.covertArrayFromList(lst);
        StringBuffer sb = new StringBuffer();;
        for (int i = 0; i < str.length; i++) {
            sb.append(str[i]);
            sb.append(" ");
        }
        System.out.println("Converted Array from List: "+
                    sb);
	

}
}
