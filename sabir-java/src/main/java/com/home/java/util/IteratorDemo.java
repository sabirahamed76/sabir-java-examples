package com.home.java.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IteratorDemo {
	public static void main(String[] srgs) {
		// Create an array list
		  Iterator<String> itr;
	      List<String> al = new ArrayList<>();
	      
	      // add elements to the array list
	      al.add("C");
	      al.add("A");
	      al.add("E");
	      al.add("B");
	      al.add("D");
	      al.add("F");

	      // Use iterator to display contents of al
	      System.out.print("Original contents of al: ");
	      itr = al.iterator();
	      
	      while(itr.hasNext()) {
	         Object element = itr.next();
	         System.out.print(element + " ");
	      }
	      System.out.println();
	}
}
