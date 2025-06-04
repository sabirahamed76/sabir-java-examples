package com.home.java.util;

import java.util.BitSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.TreeSet;

public class SetDemo {
	

	public void hashSetSample() {
		// Creating HashSet and
        // adding elements
        HashSet<String> hs = new HashSet<String>();

        hs.add("Geeks");
        hs.add("For");
        hs.add("Geeks");
        hs.add("Is");
        hs.add("Very helpful");

        // Traversing elements
        Iterator<String> itr = hs.iterator();
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }
    }
	
	public void linkedHashSetSample() {
		// Creating LinkedHashSet and
        // adding elements
        LinkedHashSet<String> lhs
            = new LinkedHashSet<String>();

        lhs.add("Geeks");
        lhs.add("For");
        lhs.add("Geeks");
        lhs.add("Is");
        lhs.add("Very helpful");

        // Traversing elements
        Iterator<String> itr = lhs.iterator();
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }
	}
	
	public void treeSetSample() {
		// Creating TreeSet and
        // adding elements
        TreeSet<String> ts = new TreeSet<String>();

        ts.add("Geeks");
        ts.add("For");
        ts.add("Geeks");
        ts.add("Is");
        ts.add("Very helpful");

        // Traversing elements
        Iterator<String> itr = ts.iterator();
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }
	}
	
	public void bitSetSample() {
		 BitSet bits1 = new BitSet(16);
	      BitSet bits2 = new BitSet(16);
	      
	      // set some bits
	      for(int i = 0; i < 16; i++) {
	         if((i % 2) == 0) bits1.set(i);
	         if((i % 5) != 0) bits2.set(i);
	      }
	     
	      System.out.println("Initial pattern in bits1: ");
	      System.out.println(bits1);
	      System.out.println("\nInitial pattern in bits2: ");
	      System.out.println(bits2);

	      // AND bits
	      bits2.and(bits1);
	      System.out.println("\nbits2 AND bits1: ");
	      System.out.println(bits2);

	      // OR bits
	      bits2.or(bits1);
	      System.out.println("\nbits2 OR bits1: ");
	      System.out.println(bits2);
	      

	      // XOR bits
	      bits2.xor(bits1);
	      System.out.println("\nbits2 XOR bits1: ");
	      System.out.println(bits2);

	}
	
	public static void main(String args[]) {
		SetDemo sd = new SetDemo();
		//Set Example
		System.out.println("=============HASH SET EXAMPLE==================");
		sd. hashSetSample();
		
		System.out.println("=============LINKED HASH SET EXAMPLE==================");
		sd.linkedHashSetSample() ;
		
		System.out.println("=============TREE SET EXAMPLE==================");
		sd.treeSetSample() ;
		
		System.out.println("=============BIT SET EXAMPLE==================");
		sd.bitSetSample() ;
	}
}
