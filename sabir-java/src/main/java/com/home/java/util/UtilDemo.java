/*
 * UtilDemo.java
 *
 * Created on December 11, 2009, 9:57 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.home.java.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.TreeMap;
public class UtilDemo {
	public static void main(String args[]){
		String strings[] = {"Jason","Emily","Lisa","Jamie","Pierre","Stanley","Gloria","Ben","Ken","Lela"};
		Arrays.sort(strings);
		List list1 = Arrays.asList(strings);
		displayList(list1);

		LinkedList list = new LinkedList();
		list.add("is");
		list.add("is");
		list.add("a");
		list.add("a");
		list.add(null);
		list.addLast("test");
		list.addFirst("This");
		displayLinkedList(list);

		HashSet set = new HashSet();
		set.add("This");
		set.add("is");
		set.add("is");
		set.add("a");
		set.add("a");
		set.add(null);
		set.add("test");
		displaySet(set);

		TreeMap map = new TreeMap();
		map.put("one","1");
		map.put("two","2");
		map.put("three","3");
		map.put("four","4");
		map.put("five","5");
		map.put("six","6");
		displayMap(map);



	}

	static void displayList(List list) {
		System.out.println("The size of the list is: "+list.size());
                System.out.println("========================");
		ListIterator i = list.listIterator(0);
		while(i.hasNext()){
			Object o = i.next();
			if(o == null) System.out.println("null");
			else System.out.println(o.toString());
		}
	}

	static void displayLinkedList(LinkedList list) {
		System.out.println("The size of the Linked list is: "+list.size());
                System.out.println("========================");
		ListIterator i = list.listIterator(0);
		while(i.hasNext()){
			Object o = i.next();
			if(o == null) System.out.println("null");
			else System.out.println(o.toString());
		}
	}

	static void displaySet(HashSet set) {
		System.out.println("The size of the set is: "+set.size());
                System.out.println("========================");
		Iterator i = set.iterator();
		while(i.hasNext()){
			Object o = i.next();
			if(o == null) System.out.println("null");
			else System.out.println(o.toString());
		}
	}

	static void displayMap(TreeMap map) {
		System.out.println("The size of the map is: "+map.size());
                System.out.println("========================");
		Collection c = map.entrySet( );
		Iterator i = c.iterator();
		while(i.hasNext()){
			Object o = i.next();
			if(o == null) System.out.println("null");
			else System.out.println(o.toString());
			}
	}


}

