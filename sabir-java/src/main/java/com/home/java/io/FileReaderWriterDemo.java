package com.home.java.io;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileReaderWriterDemo   {

	   public static void main(String args[]) {
	   
	      try {
	          File file = new File("d://workstation//workspace//workspace-java//sabir-java//test//testFile1.txt");
	          
	          //Create the file
	          if (file.createNewFile()) { 
	             System.out.println("File is created!");
	          } else {
	             System.out.println("File already exists.");
	          } 
	          
	          // Write Content
	          FileWriter writer = new FileWriter(file);
	          writer.write("Test data");
	          writer.close();
	          
	          
	          // read content
	          FileReader reader = new FileReader(file);
	          
	          int c;
	          while ((c = reader.read()) != -1) {
	        	  char ch = (char) c;
	              System.out.print(ch);
	          }
	      } catch (IOException e) {
	         System.out.print("Exception");
	      }	
	   }
	}