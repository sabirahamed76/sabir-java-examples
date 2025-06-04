package com.home.java.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;
import java.util.Vector;

public class ListDemo {
	
	public void arrayListSample() {
		// Declaring the ArrayList with
        // initial size n
        ArrayList<Integer> al = new ArrayList<Integer>();

        // Appending new elements at
        // the end of the list
        for (int i = 1; i <= 5; i++)
            al.add(i);

        // Printing elements
        System.out.println(al);

        // Remove element at index 3
        al.remove(3);

        // Displaying the ArrayList
        // after deletion
        System.out.println(al);

        // Printing elements one by one
        for (int i = 0; i < al.size(); i++)
            System.out.print(al.get(i) + " ");
        System.out.println();
	}
	
	public void linkedListSample() {
		 // Declaring the LinkedList
        LinkedList<Integer> ll = new LinkedList<Integer>();

        // Appending new elements at
        // the end of the list
        for (int i = 1; i <= 5; i++)
            ll.add(i);

        // Printing elements
        System.out.println(ll);

        // Remove element at index 3
        ll.remove(3);

        // Displaying the List
        // after deletion
        System.out.println(ll);

        // Printing elements one by one
        for (int i = 0; i < ll.size(); i++)
            System.out.print(ll.get(i) + " ");
        System.out.println();
	}
	
	public void vectorSample() {
		// Declaring the Vector
        Vector<Integer> v = new Vector<Integer>();

        // Appending new elements at
        // the end of the list
        for (int i = 1; i <= 5; i++)
            v.add(i);

        // Printing elements
        System.out.println(v);

        // Remove element at index 3
        v.remove(3);

        // Displaying the Vector
        // after deletion
        System.out.println(v);

        // Printing elements one by one
        for (int i = 0; i < v.size(); i++)
            System.out.print(v.get(i) + " ");
        System.out.println();
	}

	public void stackSample() {
		Stack<String> stack = new Stack<String>();
        stack.push("Geeks");
        stack.push("For");
        stack.push("Geeks");
        stack.push("Geeks");

        // Iterator for the stack
        Iterator<String> itr = stack.iterator();

        // Printing the stack
        while (itr.hasNext()) {
            System.out.print(itr.next() + " ");
        }

        System.out.println();

        stack.pop();

        // Iterator for the stack
        itr = stack.iterator();

        // Printing the stack
        while (itr.hasNext()) {
            System.out.print(itr.next() + " ");
        }
        System.out.println();
	}
	public static void main(String args[]) {
		ListDemo ld = new ListDemo();
		//List Example
		System.out.println("=============ARRAY LIST EXAMPLE==================");
		ld.arrayListSample();
		
		System.out.println("=============LISKED LIST EXAMPLE==================");
		ld.linkedListSample() ;
		
		System.out.println("=============VECOTR EXAMPLE==================");		
		ld.vectorSample() ;
		
		System.out.println("=============STACK EXAMPLE==================");
		ld.stackSample();
	}
}
