package com.home.java.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CollectionsDemo{
	
	public static void main(String args[]) {
		// Create list1 
        List<String> fruitList = new ArrayList<>(); 
        fruitList.add("Orange"); 
        fruitList.add("Apple"); 
        fruitList.add("Banana"); 
        fruitList.add("Mango"); 
        
        // Add one or more elements 
        Collections.addAll(fruitList, "Dates", "Grapes", "Guava"); 
        List<String> fruitList1=fruitList;
        
        System.out.println("------------List1 Before Reverse sorting------------");
        for (int i = 0; i < fruitList.size(); i++) { 
            System.out.print(fruitList.get(i) + " "); 
        } 
        System.out.println();
        
        // Minimum in the list 
        String minimum = Collections.min(fruitList); 
        System.out.println("Min value of our list : " + minimum); 
  
        // Maximum in the list 
        String maximum = Collections.max(fruitList); 
        System.out.println("Max value of our list : " + maximum); 
        
        // Sorting according list1 reverse ordering 
        Collections.sort(fruitList, Collections.reverseOrder()); 
  
 
        System.out.println("-----------List1 After sorting------------");
        for (int i = 0; i < fruitList.size(); i++) { 
            System.out.print(fruitList.get(i) + " "); 
        }  
        System.out.println();
        
        // BinarySearch on the List 
        System.out.println( 
            "------------The index of Orange is "
            + Collections.binarySearch(fruitList, "Orange")); 
  
        // BinarySearch on the List 
        System.out.println( 
            "------------The index of Apple is "
            + Collections.binarySearch(fruitList, "Apple")); 
        
  
		// Create list2 
        List<String> vegList = new ArrayList<>(); 
        vegList.add("Carrot"); 
        vegList.add("Onion"); 
        vegList.add("Tomato"); 
        vegList.add("Dates"); 
        
        // Add one or more elements 
        Collections.addAll(vegList, "Fig", "Cucumber", "Potato"); 
        
        System.out.println("------------List2 Before copying------------");
        for (int i = 0; i < vegList.size(); i++) { 
            System.out.print(vegList.get(i) + " "); 
        } 
        System.out.println();
        
  
        // Check if disjoint or not 
        System.out.println("------------Collection Disjoint value is " + Collections.disjoint(fruitList, vegList) + " due to Dates occured in both List"); 
        vegList.set(3, "Beetroot");
        System.out.println("------------Collection Disjoint value is " + Collections.disjoint(fruitList, vegList) + " due to List1 and List2 are unique after replace Dates in List1"); 
        
        
        
        //Copying From List1 to List2
        Collections.copy(fruitList1, fruitList);
        System.out.println("------------fruitList1 After copying------------ "); 
        for (int i = 0; i < fruitList1.size(); i++) { 
            System.out.print(fruitList1.get(i) + " "); 
        } 
        System.out.println();
        
        //Freeze the fruitList1
        fruitList1=Collections.unmodifiableList(fruitList1);
        //Below line commented due the list is can not be modified
        //Collections.copy(fruitList1, vegList);
        System.out.println("FruitList1="+fruitList1);
        
	}
	
}
