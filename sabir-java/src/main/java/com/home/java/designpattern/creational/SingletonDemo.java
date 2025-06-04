package com.home.java.designpattern.creational;

import java.io.Serializable;
import java.lang.reflect.Constructor;


//One Single object created
//Mark constructor as private will prevents direct instantiation
public class SingletonDemo {

	public static void main (String args[]) {

		System.out.println("-----------LazyInitializedSingleton------------");
		LazyInitializedSingletonTest();
		
		System.out.println("-----------SerializedSingleton------------");
		SerializedSingletonTest();
		
		System.out.println("-----------StaticBlockSingleton------------");
		StaticBlockSingletonTest();
		
		System.out.println("-----------ReflectionSingleton------------");
		ReflectionSingletonTest();


		
	}
	
	static void LazyInitializedSingletonTest() {
		LazyInitializedSingleton ls1 =LazyInitializedSingleton.getInstance();
		LazyInitializedSingleton ls2 =LazyInitializedSingleton.getInstance();
		System.out.println(ls1.hashCode());
		System.out.println(ls2.hashCode());
	}
	
	static void SerializedSingletonTest() {
		SerializedSingleton ss1 =SerializedSingleton.getInstance();
		SerializedSingleton ss2 =SerializedSingleton.getInstance();
		System.out.println(ss1.hashCode());
		System.out.println(ss2.hashCode());
	}
	
	static void StaticBlockSingletonTest() {
		StaticBlockSingleton sb1 =StaticBlockSingleton.getInstance();
		StaticBlockSingleton  sb2 =StaticBlockSingleton.getInstance();
		System.out.println(sb1.hashCode());
		System.out.println(sb2.hashCode());
	}
	
	static void ReflectionSingletonTest () {
	        EagerInitializedSingleton instanceOne = EagerInitializedSingleton.getInstance();
	        EagerInitializedSingleton instanceTwo = null;
	        try {
	            Constructor[] constructors = EagerInitializedSingleton.class.getDeclaredConstructors();
	            for (Constructor constructor : constructors) {
	                // This code will destroy the singleton pattern
	               constructor.setAccessible(true);
	                instanceTwo = (EagerInitializedSingleton) constructor.newInstance();
	                break;
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        System.out.println(instanceOne.hashCode());
	        System.out.println(instanceTwo.hashCode());
	    }
	
	
	
	
}


class SerializedSingleton implements Serializable {

    private static final long serialVersionUID = -7604766932017737115L;

    private SerializedSingleton(){}

    private static class SingletonHelper {
        private static final SerializedSingleton instance = new SerializedSingleton();
    }

    public static SerializedSingleton getInstance() {
        return SingletonHelper.instance;
    }
    
   public void print() {
	   System.out.println("printing from SerializedSingleton");
   }

}

class EagerInitializedSingleton {

    private static final EagerInitializedSingleton instance = new EagerInitializedSingleton();

    // private constructor to avoid client applications using the constructor
    private EagerInitializedSingleton(){}

    public static EagerInitializedSingleton getInstance() {
        return instance;
        
    }
}

class StaticBlockSingleton {

    private static StaticBlockSingleton instance;

    private StaticBlockSingleton(){}

    // static block initialization for exception handling
    static {
        try {
            instance = new StaticBlockSingleton();
        } catch (Exception e) {
            throw new RuntimeException("Exception occurred in creating singleton instance");
        }
    }

    public static StaticBlockSingleton getInstance() {
        return instance;
    }
}


class LazyInitializedSingleton {

    private static LazyInitializedSingleton instance;

    private LazyInitializedSingleton(){} // Private constructor prevents direct instantiation

    public static LazyInitializedSingleton getInstance() {
        if (instance == null) {
            instance = new LazyInitializedSingleton();
        }
        return instance;
    }
}


class ThreadSafeSingleton {

    private static ThreadSafeSingleton instance;

    private ThreadSafeSingleton(){}

    public static synchronized ThreadSafeSingleton getInstance() {
        if (instance == null) {
            instance = new ThreadSafeSingleton();
        }
        return instance;
    }

}


class BillPughSingleton {

    private BillPughSingleton(){}

    private static class SingletonHelper {
        private static final BillPughSingleton INSTANCE = new BillPughSingleton();
    }

    public static BillPughSingleton getInstance() {
        return SingletonHelper.INSTANCE;
    }
}




