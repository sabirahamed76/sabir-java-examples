package com.home.java.lang.Thread;

import java.util.List;
import java.util.Vector;

class Producer implements Runnable {
    private List list;
    public Producer(List pList) {
        list = pList;
    }
    
    public void run() {
        System.out.println("Producer started");
        for (int i = 0; i < 5000; i++) {
            list.add(Integer.toString(i));
        }    
        System.out.println("Producer completed");
    }
}


class Consumer implements Runnable {
    private List list;
    public Consumer(List pList) {
        list = pList;
    }
    
    public void run() {
        System.out.println("Consumer started");
        for (int i = 0; i < 5000; i++) {
            while (!list.remove(Integer.toString(i))) {
                // Just iterating till an element is removed
            }
        }
        System.out.println("Consumer completed");
    }
}

public class ThreadRunnableDemo {

	public static void main(String[] args) {
		List list = new Vector();
        for (int i = 0; i < 3; i++) {
            Thread p1 = new Thread(new Producer(list));
            p1.start();
        }
        for (int i = 0; i < 3; i++) {
            Thread c1 = new Thread(new Consumer(list));
            c1.start();
        }
        Thread.yield();
        while (Thread.activeCount() > 1) {
            try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}    
        }
        System.out.println(list.size());

	}

}





        