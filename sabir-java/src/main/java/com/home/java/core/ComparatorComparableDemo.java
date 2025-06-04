package com.home.java.core;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class User implements Comparator<User>, Comparable<User> {
	public String name;
	public int age;

	User() {
	}

	User(String n, int a) {
	      name = n;
	      age = a;
	   }

	   public String getName() {
	      return name;
	   }

	   public int getAge() {
	      return age;
	   }
	   
	   // Overriding the compareTo method to sort the age from java.lang.Comparable Class
	   @Override
	   public int compareTo(User u) {
	      return (this.name).compareTo(u.name);
	   }
	   
	   // Overriding the compare method from java.util.Comparator Class
	   	@Override
		public int compare(User u1, User u2) {
			int nameCompare = u1.name.compareTo(u2.name);
			return (nameCompare != 0) ? nameCompare : Integer.compare(u1.age, u2.age);
		}

	   @Override
	   public String toString() {
	      return this.name + "," + this.age;
	   }
	}



public class ComparatorComparableDemo {

	   public static void main(String args[]) {
	      // Takes a userList o User objects
	      List<User> userList = new ArrayList<>();
		   User u1= new User("ahamed", 1);
		   User u2= new User("ayisha", 2);
		   User u3= new User("yousuff", 3);
		   User u4= new User("yahyaa", 4);
		   User u5= new User("fathima", 5);
		   User u6= new User("ahamed", 6);
		   User u7= new User("ahamed", 1);
		   User u8= new User("ahaamed", 2);
		   User u9= new User("ahaamed", 7);
		   User u10= new User("ahmed", 8);
		   User u11= new User("ahmed", 5);
	      userList.add(u1);
		   userList.add(u10);
		   userList.add(u11);
		  userList.add(u9);
		  userList.add(u5);
		  userList.add(u7);
		  userList.add(u8);
	      userList.add(u4);
		  userList.add(u6);
		  userList.add(u3);
		  userList.add(u2);


	      
	       System.out.println();
	       System.out.println("Sorted by name, age: by Comparator Interface");
		   //Collections.sort(userList, new User());
		   userList.sort(Comparator.comparing(User::getName).thenComparing(User::getAge));
	;	   userList.forEach(System.out::println);


		   System.out.println();
		   System.out.println("Sorted by name: by Comparable Interface");
		   Collections.sort(userList);   // Sorts the array userList
		   System.out.println("Compareto: " + u7.compareTo(u1));
		   // printing the sorted userList of names
		   //System.out.println(userList);
		   userList.forEach(System.out::println);



		   System.out.println(" ");
	      
	   }
	}