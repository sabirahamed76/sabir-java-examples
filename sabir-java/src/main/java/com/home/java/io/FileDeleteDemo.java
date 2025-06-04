package com.home.java.io;

import java.io.File;
import java.io.IOException;

public class FileDeleteDemo {
  
   public static void deleteFiles(File dirPath) {
      File filesList[] = dirPath.listFiles();
      for(File file : filesList) {
    	  System.out.println(file.getName());
         if(file.isFile()) {
            file.delete();
         } else {
            deleteFiles(file);
         }
      }
   }
   public static void main(String args[]) throws IOException {
      
      //Creating a File object for directory
      File file = new File("d://workstation//workspace//workspace-java//sabir-java//test//");
      
      //List of all files and directories
      deleteFiles(file);
      System.out.println("Files deleted from " + file.getAbsolutePath());
   }
}