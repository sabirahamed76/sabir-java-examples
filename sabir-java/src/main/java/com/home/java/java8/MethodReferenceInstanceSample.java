/**
 * 
 */
package com.home.java.java8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author sabeer
 *
 */


//Class 1
//Helper class
//Object need to be sorted
class Person1 {

 // Attributes of a person
 private String name;
 private Integer age;

 // Constructor
 public Person1(String name, int age) {
     this.name = name;
     this.age = age;
 }

 // Getter-setter methods
 public Integer getAge() { return age; }
 public String getName() { return name; }
}

//Class 2
//Helper class
//Comparator class
class ComparisonProvider {

 // To compare with name
 public int compareByName(Person1 a, Person1 b) {
     return a.getName().compareTo(b.getName());
 }

 // To compare with age
 public int compareByAge(Person1 a, Person1 b) {
     return a.getAge().compareTo(b.getAge());
 }
}



public class MethodReferenceInstanceSample {

	public static void main(String[] args)	 {
	     // Creating an empty ArrayList of user-defined type
	     // List of person
	     List<Person1> personList = new ArrayList<>();

	     // Adding elements to above object
	     // using add() method
	     personList.add(new Person1("vicky", 24));
	     personList.add(new Person1("poonam", 25));
	     personList.add(new Person1("sachin", 19));

	     // A comparator class with multiple
	     // comparator methods
	     ComparisonProvider comparator
	         = new ComparisonProvider();

	     // Now using instance method reference
	     // to sort array by name
	     Collections.sort(personList,
	                      comparator::compareByName);

	     // Display message only
	     System.out.println("Sort by name using Object Method Reference:");

	     // Using streams
	     personList.stream()
	         .map(x -> x.getName())
	         .forEach(System.out::println);

	     // Using instance method reference
	     // to sort array by age
	     Collections.sort(personList,
	                      comparator::compareByAge);

	     // Display message only
			System.out.println();
	     System.out.println("Sort by age using Object Method Reference:");

	     personList.stream()
	         .map(x -> x.getAge() + "-"+x.getName())
	         .forEach(System.out::println);
	     
	     
	     
	     
	     
	     // Method reference to String type
	     List<String> nameList = personList.stream()
	    		    .map(Person1::getName)
	    		    .collect(Collectors.toList());
	     
	     
	     Collections.sort(nameList,
	                         String::compareToIgnoreCase);
	    
	 		System.out.println();
	 	    System.out.println("Sort by name using String Method Reference:");
	        // Printing the elements(names) on console
	 	   nameList.forEach(System.out::println);
	        
	 }

}
