package com.home.java.java8;
/**
 * @author sabeer
 *
 */
import java.util.ArrayList;
import java.util.List;

/*
In Java, Lambda expressions basically express instances of functional interfaces
(An interface with a single abstract method is called a functional interface).
Lambda Expressions in Java are the same as lambda functions
which are the short block of code that accepts input as parameters and returns a resultant value.
In Simple Lambda Expression are used to specify an Anonymous Function
which run a block of code without explicitly declaring in a method.
 */



 interface GreetingService {
	 default void defaultPrint() {
		 System.out.println("Printing default message from normal interface ...");
	 }

	 static void staticPrint() {
		 System.out.println("Printing static message from normal interface...");
	 }

	 void sayMessage(String message); //abstract method1

}

@FunctionalInterface
interface MathOperation {
	default void defaultPrint() {
		System.out.println("Printing default message from normal interface ...");
	}

	static void staticPrint() {
		System.out.println("Printing static message from normal interface...");
	}

	int operation(int a, int b); //only one abstract method
 }

 class GreetingServiceImpl implements GreetingService{
	 @Override
	 public void sayMessage(String message){
		 System.out.println("Hello " + message);
	 }

	 @Override
	 public void defaultPrint() {
		 System.out.println("Printing message from implemented class ...");
	 }

 }

//Anonymous method means no method name
//no return type
//no access modifier
public class LambdaExpressionDemo {

	 private int operate(int a, int b, MathOperation mathOperation) {
		 return mathOperation.operation(a, b);
	 }
	 
	 public static void main(String args[]) {

		//Normal way of calling the abstract method
		GreetingService greetService = new GreetingServiceImpl();
		System.out.println("Calling by implementing interface into a class and create a object and invoke the method");
		greetService.sayMessage("Sabeer");
		greetService.defaultPrint();
		System.out.println("");

		//Calling abstract method implemented in Lambda
	    GreetingService greetServiceLambda = message -> System.out.print("Hello " + message);
	    System.out.println("Calling abstract method implemented in Lambda");
		greetServiceLambda.sayMessage("Ahamed");
		System.out.println("");
		greetServiceLambda.defaultPrint();//Method implementation inside interface rather implementation class
		GreetingService.staticPrint();//Static Method implementation inside interface so object directly calls
	    System.out.println("=============");
	    
	    //with type declaration anonymous function by lambda
	    MathOperation addition = (int a, int b) -> a + b;
			
	    //with out type declaration anonymous function by lambda
	    MathOperation subtraction = (a, b) -> a - b;
			
	    //with return statement along with curly braces anonymous function by lambda
	    MathOperation multiplication = (int a, int b) -> { return a * b; };
			
	    //without return statement and without curly braces anonymous function by lambda
	    MathOperation division = (int a, int b) -> a / b;
	    

		//Create local object and call method to pass the Interface object 
	    System.out.println("Create local object and calling the method by passing the Interface object as a parameter");
	    LambdaExpressionDemo tester = new LambdaExpressionDemo();
	    System.out.println("10 + 5 = " + tester.operate(10, 5, addition));
	    System.out.println("10 - 5 = " + tester.operate(10, 5, subtraction));
	    System.out.println("10 x 5 = " + tester.operate(10, 5, multiplication));
	    System.out.println("10 / 5 = " + tester.operate(10, 5, division));
		System.out.println("=============");

		 //Calling directly the objects method where implementation in lambda expression
		 System.out.println("Calling directly the objects method where implementation in lambda expression");
		 System.out.println("10 + 5 = " + addition.operation(10,5));
		 System.out.println("10 - 5 = " + subtraction.operation(10,5));
		 System.out.println("10 * 5 = " + multiplication.operation(10,5));
		 System.out.println("10 / 5 = " + division.operation(10,5));
		 System.out.println("=============");


		 //Another Lambda Example
	     List<String> names = new ArrayList<>();
	      names.add("Mahesh");
	      names.add("Suresh");
	      names.add("Ramesh");
	      names.add("Naresh");
	      names.add("Kalpesh");

	      System.out.println("Printing using for each loop");
	      // Approach 1: for loop to print all elements
	      for (String name: names) {
	         System.out.println(name);
	      }

	      System.out.println("Printing using for each loop with lambda expression");
	      // Approach 2: lambda expression to print the elements in for each loop
	      names.forEach(name->System.out.println(name));

	      System.out.println("Printing using for each loop with method reference" );
	      // Approach 3: Method reference to print elements in for each loop
	      names.forEach(System.out::println);
	      
	 }
	
	 
}