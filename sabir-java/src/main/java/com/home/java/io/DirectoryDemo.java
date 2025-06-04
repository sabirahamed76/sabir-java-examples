package com.home.java.io;

import java.io.File;

public class DirectoryDemo  {
	   public static void main(String args[]) {
		   String dirName="d://workstation//workspace//workspace-java//sabir-java//test//";
		   createDir(dirName) ;
		   listDir(dirName);
		   deleteDir(dirName);
	   }
	   
	   
	   public static void createDir(String dirName) {
		      File directory = new File(dirName);

		      // Create directory now.
		      directory.mkdirs();

		      // create new file object
		      File file = new File(dirName+"//testFile1.txt");
		      System.out.println(file.exists());

	   }
	   
	   public static void listDir(String dirName) {
		   File file = null;
		      String[] paths;
		  
		      try {      
		         // create new file object
		         file = new File(dirName);

		         // array of files and directory
		         paths = file.list();

		         // for each name in the path array
		         for(String path:paths) {
		            // prints filename and directory name
		            System.out.println(path);
		         }
		      } catch (Exception e) {
		         // if any error occurs
		         e.printStackTrace();
		      }
	   }

	   public static void deleteDir(String dirName) {
		   File file = new File(dirName);
		      if(file.exists()) {
		         boolean success = file.delete();

		         if (success) {
		            System.out.println("The directory has been successfully deleted."); 
		         }else {
		            System.out.println("The directory deletion failed.");
		         }        
		      }else {
		         System.out.println("The directory is not present."); 
		      }
	   }
	}