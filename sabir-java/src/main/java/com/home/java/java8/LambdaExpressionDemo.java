package com.home.java.java8;
/**
 * @author sabeer
 *
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
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

class Fruit {
	public String name;
	public Double price;

	public Fruit(String name, Double price) {
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double age) {
		this.price = age;
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
		System.out.println("Normal Way of Calling by implementing interface into a class and create a object and invoke the method");
		greetService.sayMessage("Sabeer");
		greetService.defaultPrint();
		System.out.println("");

		//1 Lambda with Functional Interface for Anonymous Function
		//Calling abstract method using Lambda// System.out.println("=================================================");
	    System.out.println("1 Lambda with Functional Interface for Anonymous Function so Calling abstract method directly using Lambda");
		System.out.println("=================================================");
		GreetingService greetServiceLambda = message -> System.out.print("Hello " + message);
		greetServiceLambda.sayMessage("Ahamed");
		System.out.println("");
		greetServiceLambda.defaultPrint();//Method implementation inside interface rather implementation class
		GreetingService.staticPrint();//Static Method implementation inside interface so object directly calls
	    System.out.println("----------------------------------------");
	    
	    //with type declaration anonymous function by lambda
	    MathOperation addition = (int a, int b) -> { return a + b;};
			
	    //with out type declaration anonymous function by lambda
	    MathOperation subtraction = (a, b) -> { return a - b;};
			
	    //with return statement along with curly braces anonymous function by lambda
	    MathOperation multiplication = ( a,  b) -> { return a * b; };
			
	    //without return statement and without curly braces anonymous function by lambda
	    MathOperation division = ( a,  b) -> a / b;
	    

		//Create local object and call method to pass the Interface object 
	    System.out.println("Create local object and calling the method by passing the Interface object as a parameter");
	    LambdaExpressionDemo tester = new LambdaExpressionDemo();
	    System.out.println("10 + 5 = " + tester.operate(10, 5, addition));
	    System.out.println("10 - 5 = " + tester.operate(10, 5, subtraction));
	    System.out.println("10 x 5 = " + tester.operate(10, 5, multiplication));
	    System.out.println("10 / 5 = " + tester.operate(10, 5, division));
		System.out.println("----------------------------------------------");

		 //Calling directly the objects method where implementation in lambda expression
		 System.out.println("Calling directly the objects method where implementation in lambda expression");
		 System.out.println("10 + 5 = " + addition.operation(10,5));
		 System.out.println("10 - 5 = " + subtraction.operation(10,5));
		 System.out.println("10 * 5 = " + multiplication.operation(10,5));
		 System.out.println("10 / 5 = " + division.operation(10,5));
		 System.out.println("");


		 //2 Lambda with Collections

		 System.out.println("=================================================");
		 System.out.println("2 Lambda with Collections");
		 System.out.println("=================================================");
		 List<Fruit> fruits = new ArrayList<Fruit>();
		 fruits.add(new Fruit("Mango",12.5));
		 fruits.add(new Fruit("Date",5.0));
		 fruits.add(new Fruit("Avacado",10.0));
		 fruits.add(new Fruit("JackFruit",3.50));
		 fruits.add(new Fruit("DragonFruit",3.0));
		 fruits.add(new Fruit("Apple",5.0));
		 fruits.add(new Fruit("Apple",1.50));
		 fruits.add(new Fruit("Durian",10.0));
		 fruits.add(new Fruit("Orange",5.0));
		 fruits.add(new Fruit("Banana",4.0));
		 System.out.println("Sort and Print using for each loop with lambda expression");
		 //fruits.sort((f1, f2) -> f1.name.compareTo(f2.name));

		 fruits.sort(Comparator.comparing(Fruit::getName).thenComparing(Fruit::getPrice));
		 fruits.forEach(fruit->System.out.println(fruit.name+"........"+fruit.price));

		 //3 Lambda with Stream
		 System.out.println("=================================================");
		 System.out.println("3 Lambda with Stream");
		 System.out.println("=================================================");
		 List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
		 numbers.stream()
				 .filter(n -> n % 2 == 0)
				 .forEach(System.out::println); // Prints even numbers
	
	 }
	
	 
}