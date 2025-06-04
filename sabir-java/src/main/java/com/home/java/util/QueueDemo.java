package com.home.java.util;

import java.util.ArrayDeque;
import java.util.PriorityQueue;

public class QueueDemo {
	

	
	public void priorityQueueSample() {
		// Creating empty priority queue
        PriorityQueue<Integer> pQueue
            = new PriorityQueue<Integer>();

        // Adding items to the pQueue using add()
        pQueue.add(10);
        pQueue.add(20);
        pQueue.add(15);        
        System.out.println(pQueue); 
        
        // Printing the top element of PriorityQueue
        System.out.println("Printing the top element of PriorityQueue "+ pQueue.peek());

        System.out.println(pQueue);
        // Printing the top element and removing it
        // from the PriorityQueue container
        System.out.println("Printing and Remove the top element of PriorityQueue "+ pQueue.poll());
        System.out.println(pQueue);
        
	}
	
	public void arrayQueueSample() {
		// Initializing an deque
        ArrayDeque<Integer> de_que
            = new ArrayDeque<Integer>(10);

        // add() method to insert
        de_que.add(10);
        de_que.add(20);
        de_que.add(30);
        de_que.add(40);
        de_que.add(50);

        System.out.println(de_que);

        // clear() method
        de_que.clear();

        // addFirst() method to insert the
        // elements at the head
        de_que.addFirst(564);
        de_que.addFirst(291);

        // addLast() method to insert the
        // elements at the tail
        de_que.addLast(24);
        de_que.addLast(14);

        System.out.println(de_que);
	}
	
	public static void main(String args[]) {
		QueueDemo qd = new QueueDemo();
		//Queue Example
		System.out.println("=============PRIORITY QUEUE EXAMPLE==================");
		qd.priorityQueueSample();
		
		System.out.println("=============ARRAY QUEUE EXAMPLE==================");
		qd.arrayQueueSample();
		
	}
}
