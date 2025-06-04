package com.home.java.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author sabeer
 */
class Student{
   String name;
   Student(String name){
      this.name = name;
   }
   public String toString(){
      return  this.name;
   }
}
   
public class MethodReferenceDemo {

   public static <T> T mergeThings(T a, T b, BiFunction<T, T, T> merger) {
      return merger.apply(a, b);
   }

   public static String appendStrings(String a, String b) {
      return a + b;
   }

   public String appendStrings2(String a, String b) {
      return a + b;
   }

   public static void main(String args[]) {

      MethodReferenceDemo myApp = new MethodReferenceDemo();

      System.out.println("Calling the method mergeThings with a lambda expression");
      System.out.println(MethodReferenceDemo.
              mergeThings("Saber ", "Ahamed!", (a, b) -> a + b));

      System.out.println("Method Reference to a static method");
      System.out.println(MethodReferenceDemo.
              mergeThings("Saber ", "Ahamed!", MethodReferenceDemo::appendStrings));

      System.out.println("Method Reference to an instance method of a particular object");
      System.out.println(MethodReferenceDemo.
              mergeThings("Saber ", "Ahamed!", myApp::appendStrings2));

      System.out.println("Method Reference to an instance method of an arbitrary object of a particular type");
      System.out.println(MethodReferenceDemo.
              mergeThings("Saber ", "Ahamed!", String::concat));

      System.out.println("====================================================");

      System.out.println("Method Reference to a Static Method");
      System.out.println("----------------------------------");
      Function<String, String> formatValue = str -> String.format(str).concat("!");//Lambda Example
      BiFunction<String, Object[], String> formatValue1 = String::format;//Method Reference Example
      System.out.println("Formatted value by Lambda : "+formatValue.apply("சபீர் அஹமத்"));
      System.out.println("Formatted value by Method Reference : "+formatValue1.apply("சபீர் அஹமத்!", new Object[]{""}));
      System.out.println("");

      System.out.println("Method Reference to an Instance Method of a Particular Object");
      System.out.println("----------------------------------");
      String str1 = "saber";
      String str2 = "ahamed";
      Supplier<String> supplier = () -> str1.toUpperCase();//Lambda Example
      Supplier<String> supplier1 = str2::toUpperCase;//Method Reference Example
      System.out.println("UpperCase by Lambda : "+supplier.get());
      System.out.println("UpperCase by Method Reference : "+supplier1.get());
      System.out.println("");

      System.out.println("Method Reference to an Instance Method of an Arbitrary Object of a Particular Type");
      System.out.println("----------------------------------");
      List<String> list = Arrays.asList("apple", "banana", "cherry");
      System.out.println("ArrayList Iteration by Lambda ");
      list.forEach(s -> System.out.println(s));//Lambda Example
      System.out.println("ArrayList Iteration by Method Reference ");
      list.forEach(System.out::println);//Method Reference Example
      System.out.println("");

      System.out.println("Method Reference to a Constructor");
      System.out.println("----------------------------------");
      Supplier<List<String>> listSupplier = () -> new ArrayList<>();//Lambda Example
      Supplier<List<String>> listSupplier1 = ArrayList::new;//Method Reference Example
      List<String> list1 = listSupplier.get();
      List<String> list2 = listSupplier1.get();
      list1.add("Apple");
      list1.add("Banana");
      list2.add("Orange");
      list2.add("Mango");
      System.out.println("Constructor List by Lambda : " + list1);
      System.out.println("Constructor List by MethodReference : " + list2);
      System.out.println("");

      System.out.println("Method Reference to create the list of student objects from array");
      System.out.println("----------------------------------");
      //Create a list from String Array
      List<String> studentNames = Arrays.asList("Saber","Ayisha","Yousuff","Yahyaa","Fathima");

      //Convert List of Strings into Student Array
      Student[] students =  studentNames.stream()
              .map(Student::new)
              .toArray(Student[]::new);

      //Convert Student Array to Student List
      List<Student> studentList = Arrays.asList(students);
      System.out.println("Print List using Lambda");
      studentList.forEach(name -> System.out.println(name));
      System.out.println("Print List using Method Reference");
      studentList.forEach(System.out::println);
      
   }
}


