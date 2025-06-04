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
class Movie  {
 private double rating;
 private String name;
 private int year;


 // Constructor
 public Movie(String nm, double rt, int yr)
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
class RatingCompare implements Comparator<Movie> {
 public int compare(Movie m1, Movie m2)
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
class YearCompare implements Comparator<Movie> {
public int compare(Movie m1, Movie m2)
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
class NameCompare implements Comparator<Movie> {
 public int compare(Movie m1, Movie m2)
 {
     return m1.getName().compareTo(m2.getName());
 }
}

//Driver class
public class ComparatorDemo {
 public static void main(String[] args)
 {
     ArrayList<Movie> list = new ArrayList<Movie>();
     list.add(new Movie("Force Awakens", 8.3, 2015));
     list.add(new Movie("Star Wars", 8.7, 1977));
     list.add(new Movie("Empire Strikes Back", 8.8, 1980));
     list.add(new Movie("Return of the Jedi", 8.4, 1983));

     //(1) Create an object of ratingCompare
     //(2) Call Collections.sort
     //(3) Print Sorted list
     System.out.println("Sorted by rating");
     System.out.println("==========");
     RatingCompare ratingCompare = new RatingCompare();
     Collections.sort(list, ratingCompare);
     for (Movie movie : list)
         System.out.println(movie.getRating() + " "
                            + movie.getName() + " "
                            + movie.getYear());

     // Call overloaded sort method with RatingCompare
     System.out.println("\nSorted by name");
     System.out.println("==========");
     NameCompare nameCompare = new NameCompare();
     Collections.sort(list, nameCompare);
     for (Movie movie : list)
         System.out.println(movie.getName() + " "
                            + movie.getRating() + " "
                            + movie.getYear());

     // Call overloaded sort method with YearCompare
     System.out.println("\nSorted by year");
     System.out.println("==========");
     YearCompare yearCompare = new YearCompare();
     Collections.sort(list, yearCompare);
     for (Movie movie : list)
         System.out.println(movie.getYear() + " "
                            + movie.getRating() + " "
                            + movie.getName() + " ");
 }
}