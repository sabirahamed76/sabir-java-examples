package com.home.java.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileBufferedReaderWriterDemo {
	  
	   public static void main(String args[]) throws IOException {
	      BufferedWriter out = new BufferedWriter (new FileWriter("d://workstation//workspace//workspace-java//sabir-java//test//testFile1.txt"));
	      out.write("test data1");
	      out.close();
	      
	      BufferedReader in = new BufferedReader (new FileReader("d://workstation//workspace//workspace-java//sabir-java//test//testFile1.txt"));
	      System.out.println(in.readLine());
	      in.close();
	      
	      
	   }
	}