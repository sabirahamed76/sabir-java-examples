package com.home.java.core;

interface GreetingNormalInterace {
    void sayGreeting();
}

@FunctionalInterface
interface GreetingFunctionalInterface {
    void sayGreeting();
}

class WelcomeClass {
    public void sayWelcome() {
    }
}

abstract class Engine {
   public static String engineType=null;
   public abstract void getEngineType()  ;
}

class Vehicle {
   public String engineType=null;
    public void transport(Engine e) {
	   e.getEngineType();
	   this.engineType=e.engineType;
   }
}

public class AnonymousClassDemo {
   public static void main(String args[]) {
       //Anonymous class without implementing interface
       GreetingNormalInterace greetingNormalInterace = new GreetingNormalInterace() {
           public void sayGreeting() {
               System.out.println("Hello from an anonymous Interface!");
           }
       };
       greetingNormalInterace.sayGreeting();

       //Anonymous class without implementing interface
       WelcomeClass welcomeClass = new WelcomeClass() {
           public void sayWelcome() {
               System.out.println("Hello from an anonymous class!");
           }
       };
       welcomeClass.sayWelcome();

       //Anonymous class without implementing Functional interface
       GreetingFunctionalInterface greetingFunctionalInterface = () -> System.out.println("Hello from an anonymous Functional Interface!");
       greetingFunctionalInterface.sayGreeting();

       System.out.println();

       //Another complicated Example
       Vehicle v1 = new Vehicle();
       Vehicle v2 = new Vehicle();
       v1.transport(new Engine() {
          @Override
          public void getEngineType() {
         	 engineType="Turbo Engine";
          }
       });
       v2.transport(new Engine() {
          @Override
          public void getEngineType() {
         	 engineType="Normal Engine";
          }
       });
       System.out.println("Vechicle 1 Engine Type="+v1.engineType);
       System.out.println("Vechicle 2 Engine Type="+v2.engineType);




   }
}