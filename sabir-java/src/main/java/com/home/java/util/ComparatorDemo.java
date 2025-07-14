package com.home.java.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
/*
 * 
 * Comparator Interface has compare method to sort by multiple column
 * 
 */
//A class 'Movie' that implements Comparable
class Book {
 private double rating;
 private String name;
 private int year;


 // Constructor
 public Book(String nm, double rt, int yr)
 {
     this.name = nm;
     this.rating = rt;
     this.year = yr;
 }

 // Getter methods for accessing private data
 public double getRating() { return rating; }
 public String getName() { return name; }
 public int getYear() { return year; }
}

//Class to compare Movies by ratings
class RatingCompare implements Comparator<Book> {
     public int compare(Book m1, Book m2)
     {
         if (m1.getRating() < m2.getRating())
             return -1;
         if (m1.getRating() > m2.getRating())
             return 1;
         else
             return 0;
     }
}

//Class to compare Movies by year
class YearCompare implements Comparator<Book> {
    public int compare(Book m1, Book m2)
    {
       if (m1.getYear() < m2.getYear())
           return -1;
       if (m1.getYear() > m2.getYear())
           return 1;
       else
           return 0;
    }
}


//Class to compare Movies by name
class NameCompare implements Comparator<Book> {
 public int compare(Book m1, Book m2)
 {
     return m1.getName().compareTo(m2.getName());
 }
}


public class ComparatorDemo {
 public static void main(String[] args)
 {
     ArrayList<Book> list = new ArrayList<Book>();
     list.add(new Book("Revenant", 8.0, 2015));
     list.add(new Book("Mad Max: Fury Road", 8.1, 2015));
     list.add(new Book("Inside Out", 8.2, 2015));
     list.add(new Book("Big Short", 7.9, 2015));
     list.add(new Book("Force Awakens", 8.3, 2015));
     list.add(new Book("Star Wars", 8.7, 1977));
     list.add(new Book("Empire Strikes Back", 8.8, 1980));
     list.add(new Book("Return of the Jedi", 8.4, 1983));

     //(1) Create an object of ratingCompare
     //(2) Call Collections.sort
     //(3) Print Sorted list
     System.out.println("Sorted by rating");
     System.out.println("==========");
     RatingCompare ratingCompare = new RatingCompare();
     Collections.sort(list, ratingCompare);
     for (Book book : list)
         System.out.println(book.getRating() + " "
                            + book.getName() + " "
                            + book.getYear());

     // Call overloaded sort method with RatingCompare
     System.out.println("\nSorted by name");
     System.out.println("==========");
     NameCompare nameCompare = new NameCompare();
     Collections.sort(list, nameCompare);
     for (Book book : list)
         System.out.println(book.getName() + " "
                            + book.getRating() + " "
                            + book.getYear());

     // Call overloaded sort method with YearCompare
     System.out.println("\nSorted by year");
     System.out.println("==========");
     YearCompare yearCompare = new YearCompare();
     Collections.sort(list, yearCompare);
     for (Book movie : list)
         System.out.println(movie.getYear() + " "
                            + movie.getRating() + " "
                            + movie.getName() + " ");
 }
}