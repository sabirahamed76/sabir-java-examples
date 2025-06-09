package com.home.java.lang.Thread;


/*In Java, daemon threads are low-priority threads that run in the background 
to perform tasks such as garbage collection or provide services to user threads. 
The life of a daemon thread depends on the mercy of user threads, 
meaning that when all user threads finish their execution, 
the Java Virtual Machine (JVM) automatically terminates the daemon thread.*/

public class ThreadDemonDemo extends Thread
{
	public ThreadDemonDemo(String name){
		super(name);
	}

	public void run()
	{
		// Checking whether the thread is Daemon or not
		if(Thread.currentThread().isDaemon())
		{
			System.out.println(getName() + " is Daemon thread");
		}
		
		else
		{
			System.out.println(getName() + " is User thread");
		}
	}
	
	public static void main(String[] args)
	{
	
		ThreadDemonDemo t1 = new ThreadDemonDemo("t1");
		ThreadDemonDemo t2 = new ThreadDemonDemo("t2");
		ThreadDemonDemo t3 = new ThreadDemonDemo("t3");
	
		// Setting user thread t1 to Daemon
		t1.setDaemon(true);
			
		// starting first 2 threads
		t1.start();
		t2.start();

		// Setting user thread t3 to Daemon
		t3.setDaemon(true);
		t3.start();	 
	}
}
