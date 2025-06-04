package com.home.java.designpattern.creational;


class Singleton {

	   private static Singleton singleton = new Singleton( );

	   /* A private Constructor prevents any other
	    * class from instantiating.
	    */
	   private Singleton() { }

	   /* Static 'instance' method */
	   public static Singleton getInstance( ) {
	      return singleton;
	   }

	   /* Other methods protected by singleton-ness */
	   protected void demoMethod( ) {
	      System.out.println("demoMethod for singleton");
	   }
	}
	public class SingletonClassDemo {

	   public static void main(String[] args) {
	      Singleton tmp = Singleton.getInstance( );
	      tmp.demoMethod( );
	   }
	}