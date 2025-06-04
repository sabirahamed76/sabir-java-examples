package com.home.java.util;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

public class MapDemo {

	
	

	public void hashMapSample() {
		
		// Creating HashMap and
        // adding elements
        HashMap<Integer, String> hm
            = new HashMap<Integer, String>();

        hm.put(1, "Geeks");
        hm.put(2, "For");
        hm.put(3, "Geeks");

        // Finding the value for a key
        System.out.println("Value for 1 is " + hm.get(1));

        // Traversing through the HashMap
        for (Map.Entry<Integer, String> e : hm.entrySet())
            System.out.println(e.getKey() + " "
                               + e.getValue());
	}
	
	public void hashTableSample() {
		// Create an empty Hashtable
        Hashtable<String, Integer> ht = new Hashtable<>();

        // Add elements to the hashtable
        ht.put("apple", 10);
        ht.put("orange", 30);
        ht.put("banana", 20);
        ht.put("grapes", 40);

        // Print size and content
        System.out.println("Size of hashtable is: " + ht.size());
        System.out.println(ht);

        // Check if a key is present and if
        // present, print value
        if (ht.containsKey("apple")) {
            Integer a = ht.get("apple");
            System.out.println("value for key"
                               + " \"apple\" is: " + a);
        }
        
        ht.remove("grapes");
        // Iterating using enhanced for loop
        for (Map.Entry<String, Integer> e : ht.entrySet())
            System.out.println(e.getKey() + " "
                               + e.getValue());
	}
	
	
	public void treeMapSample() {
		// Create a hash map
	      SortedMap<String, Double> map = new TreeMap<>();

	      // Put elements to the map
	      map.put("Zara", Double.valueOf(3434.34));
	      map.put("Mahnaz", Double.valueOf(123.22));
	      map.put("Ayan", Double.valueOf(1378.00));
	      map.put("Daisy", Double.valueOf(99.22));
	      map.put("Qadir", Double.valueOf(-19.08));
	      
	      // Get a set of the entries
	      Set<Map.Entry<String, Double>> set = map.entrySet();
	      
	      // Get an iterator
	      Iterator<Map.Entry<String, Double>> i = set.iterator();
	     
	      // Display elements 
	      while(i.hasNext()) {
	         Map.Entry<String, Double> me = i.next();
	         me.setValue(me.getValue() * 10);
	         System.out.print(me.getKey() + ": ");
	         System.out.println(me.getValue());
	      }
	}
	
	public static void main(String args[]) {
		MapDemo md = new MapDemo();
		//Map Example
		System.out.println("=============HASH MAP EXAMPLE==================");
		md.hashMapSample();
		
		System.out.println("=============HASH TABLE EXAMPLE==================");
		md.hashTableSample();
		
		System.out.println("=============TREE MAP EXAMPLE==================");
		md.treeMapSample();
		
		
	}
}
