package com.home.java.java8;

/**
 * @author sabeer
 *
 */
//Java Program to Illustrate How One can use
//Static method reference
//To Sort with Custom Comparator

//Importing required classes
import java.io.*;
import java.util.*;

//Class 1
//Helper class
//Object need to be sorted
class Person {

	private String name;
	private Integer age;

	// Constructor
	public Person(String name, int age)
	{
		// This keyword refers to current instance itself
		this.name = name;
		this.age = age;
	}

	// Getter-setters
	public Integer getAge() { return age; }
	public String getName() { return name; }
}




public class MethodReferenceStaticSample {


		// Static method to compare with name
		public static int compareByName(Person a, Person b)	{
			return a.getName().compareTo(b.getName());
		}


		// Static method to compare with age
		public static int compareByAge(Person a, Person b){
			return a.getAge().compareTo(b.getAge());
		}


		public static void main(String[] args)
		{

			// Creating an empty ArrayList of user-defined type
			// List of person
			List<Person> personList = new ArrayList<>();

			// Adding elements to above List
			// using add() method
			personList.add(new Person("vicky", 24));
			personList.add(new Person("poonam", 25));
			personList.add(new Person("sachin", 19));

			// Using static method reference to
			// sort array by name
			Collections.sort(personList, MethodReferenceStaticSample::compareByName);

			// Display message only
			System.out.println("Sort by name :");

			// Using streams over above object of Person type
			personList.stream()
				.map(x -> x.getName())
				.forEach(System.out::println);

			// Now using static method reference
			// to sort array by age
			Collections.sort(personList, MethodReferenceStaticSample::compareByAge);

			// Display message only
			System.out.println();
			System.out.println("Sort by age :");

			// Using streams over above object of Person type
			personList.stream()
				.map(x -> x.getAge() + "-"+x.getName())
				.forEach(System.out::println);
		}

}
