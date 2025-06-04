package com.home.java.utils.zip;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipUtil {

	
	static public void zipFolder(String srcFolder, String destZipFile) throws Exception {
	    ZipOutputStream zip = null;
	    FileOutputStream fileWriter = null;

	    fileWriter = new FileOutputStream(destZipFile);
	    zip = new ZipOutputStream(fileWriter);

	    addFolderToZip("", srcFolder, zip);
	    zip.flush();
	    zip.close();
	  }

	static public void addFolderFilesToZip(String srcFolder, String destZipFile) throws Exception {
	    ZipOutputStream zip = null;
	    FileOutputStream fileWriter = null;

	    fileWriter = new FileOutputStream(destZipFile);
	    zip = new ZipOutputStream(fileWriter);

	    File folder = new File(srcFolder);

	    for (String fileName : folder.list()) {
	    	
	    	String srcFile = srcFolder + File.separator+ fileName;
	        File fileOrFolder = new File(srcFile);
		    if (!fileOrFolder.isDirectory()) {
		      byte[] buf = new byte[1024];
		      int len;
		      FileInputStream in = new FileInputStream(srcFile);
		      zip.putNextEntry(new ZipEntry(fileOrFolder.getName()));
		      while ((len = in.read(buf)) > 0) {
		        zip.write(buf, 0, len);
		      }
		    }
		    
	    }
	    
	    zip.flush();
	    zip.close();
	    fileWriter.close();
	  }
	
	public static void unZipFile(String srcZipAbsPath, String zipFile, String destPath) throws Exception{
		ZipInputStream zis = null;
		FileInputStream fis = null;
		try {
			File sourceF= new File(srcZipAbsPath);
	        File destD = new File(destPath);
	        if(!destD.exists()) {
	        	destD.mkdirs();
	        }
	        int BUFFER_SIZE = 2048;

	       
	
	        fis = new FileInputStream(sourceF);
	        zis = new ZipInputStream(new BufferedInputStream(fis));
	
	        ZipEntry entry = null;
	        while((entry = zis.getNextEntry()) != null) {
                String outputFilename = destPath + File.separator + entry.getName();
                System.out.println("About to extract file: " + entry.getName());
                BufferedOutputStream dest = null;
                int count;
                byte data[] = new byte[BUFFER_SIZE];
                FileOutputStream fos = null;
                try {
	                fos = new FileOutputStream(outputFilename);
	                dest = new BufferedOutputStream(fos, BUFFER_SIZE);
	
	                while((count = zis.read(data, 0, BUFFER_SIZE)) != -1)  {
	                    dest.write(data, 0, count);
	                }
                } finally {
                	//dont close fos, otherwise it will throw exception: dest.close() will take care of fos
                	if(dest != null) {
		                dest.flush();
		                dest.close();
                	}
                }
            }            
		} finally {
			if(fis != null) {
				fis.close();
			}
			if(zis != null) {
				zis.close();
			}
		}

	}
	
	static private void addFileToZip(String path, String srcFile, ZipOutputStream zip)
	      throws Exception {

	    File folder = new File(srcFile);
	    if (folder.isDirectory()) {
	      addFolderToZip(path, srcFile, zip);
	    } else {
	      byte[] buf = new byte[1024];
	      int len;
	      FileInputStream in = new FileInputStream(srcFile);
	      zip.putNextEntry(new ZipEntry(path + File.separator + folder.getName()));
	      while ((len = in.read(buf)) > 0) {
	        zip.write(buf, 0, len);
	      }
	    }
	}
	
	static private void addFolderToZip(String path, String srcFolder, ZipOutputStream zip)
	      throws Exception {
	    File folder = new File(srcFolder);

	    for (String fileName : folder.list()) {
	      if (path.equals("")) {
	        addFileToZip(folder.getName(), srcFolder + File.separator+ fileName, zip);
	      } else {
	        addFileToZip(path + File.separator + folder.getName(), srcFolder + File.separator + fileName, zip);
	      }
	    }
	}

	
}
