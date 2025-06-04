package com.home.java.utils.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.log4j.Logger;

/**
 *   File Utility Class
 */
public class FileUtil
{
	private static final Logger logger = Logger.getLogger(FileUtil.class);
	private static final String FORWARD_SLASH = "/";
	private static final SimpleDateFormat DATE_FRMT_YYMMDD = new SimpleDateFormat("yyMMdd");
	private static final SimpleDateFormat DATE_FRMT_HHMMSS = new SimpleDateFormat("HHmmss");
	private static int indentLevel = -1;
	
	public static String constructLocalPath(String txHome, String mode, String clientCode,  String username, Date today){
	 	
		StringBuffer outPathFileBuf = new StringBuffer();
		
		outPathFileBuf.append(txHome).append(FORWARD_SLASH).append(clientCode);
		File dir = new File(outPathFileBuf.toString());
		if(!dir.exists()){
			dir.mkdir();									
		}
		
		outPathFileBuf.append(FORWARD_SLASH);	
		outPathFileBuf.append(mode); //IMPORT/EXPORT
		dir = new File(outPathFileBuf.toString());
		if(!dir.exists()){
			dir.mkdir();									
		}
		
		outPathFileBuf.append(FORWARD_SLASH);		
		outPathFileBuf.append(DATE_FRMT_YYMMDD.format(today));
		dir = new File(outPathFileBuf.toString());
		if(!dir.exists()){
			dir.mkdir();									
		}
		
		outPathFileBuf.append(FORWARD_SLASH);
		outPathFileBuf.append(username);
		dir = new File(outPathFileBuf.toString());
		if(!dir.exists()){
			dir.mkdir();									
		}
		
		outPathFileBuf.append(FORWARD_SLASH);			
		outPathFileBuf.append(DATE_FRMT_HHMMSS.format(today));
		dir = new File(outPathFileBuf.toString());
		if(!dir.exists()){
			dir.mkdir();									
		}
		return outPathFileBuf.toString();
	}

    public static File getTemporaryDirectory() throws IOException
    {
        File parentFile = null;
        String uniqueName = Long.toString(System.currentTimeMillis());
        File temporaryFile = File.createTempFile(uniqueName, null);
        parentFile = temporaryFile.getParentFile();
        temporaryFile.delete();
        if (parentFile == null || !parentFile.isDirectory())
        {
            String message = "getTemporaryDirectory : cannot determine temporary system directory";
            throw new IOException(message);
        }
        File temporaryDirectory = new File(parentFile, Long.toString((Calendar.getInstance().getTime().getTime())));
        temporaryDirectory.mkdir();
        return temporaryDirectory;
    }

    public static void copy(String p_inputFile, String p_outputFile) throws IOException
    {
        FileUtil.persistToFileSystem(new FileInputStream(p_inputFile), p_outputFile);
    }

    public static void persistToFileSystem(InputStream p_fileContent, String p_fileName) throws IOException
    {
        FileOutputStream fos = new FileOutputStream(p_fileName);
        byte[] buffer = new byte[1000];
        int bytesReadCount = 0;
        while ((bytesReadCount = p_fileContent.read(buffer, 0, buffer.length)) != -1)
        {
            fos.write(buffer, 0, bytesReadCount);
        }
        p_fileContent.close();
        fos.close();
    }

    public static void persistToFileSystem(String p_fileName, String p_fileContent) throws IOException
    {
        persistToFileSystem(p_fileContent.getBytes(), new File(p_fileName));
    }

    public static void persistToFileSystem(byte[] p_fileContent, String p_fileName) throws IOException
    {
        persistToFileSystem(p_fileContent, new File(p_fileName));
    }

    public static void persistToFileSystem(String p_fileContent, File p_file) throws IOException
    {
        persistToFileSystem(p_fileContent.getBytes(), p_file);
    }

    public static void persistToFileSystem(InputStream p_fileContent, File p_file) throws IOException
    {
        persistToFileSystem(StreamUtil.getBytes(p_fileContent), p_file);
    }

    public static void persistToFileSystem(byte[] p_fileContent, File p_file) throws IOException
    {
        FileOutputStream fos = new FileOutputStream(p_file);
        fos.write(p_fileContent);
        fos.close();
    }

       public static InputStream getStreamFromClassPathOrFileSystem(String p_fileName) throws FileNotFoundException
    {
        //InputStream is = FileUtilities.class.getClassLoader().getResourceAsStream(p_fileName);
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(p_fileName);
        if (is == null)
        {
            File file = new File(p_fileName);
            if (file.exists())
            {
                is = new FileInputStream(file);
            }
            else
            {
                throw new FileNotFoundException("File '" + p_fileName + "' not found");
            }
        }
        return is;
    }
  
    
	public static boolean moveFileToSubdirectory(File file, String subDirectoryName) {
		
		File dir = new File(file.getParent() + File.separator + subDirectoryName);

		if (!dir.exists()) {
			dir.mkdir();
		}
		boolean result = file.renameTo(new File(dir, file.getName()));
		if (!result) {
			String msg = "Unable to move the file " + file.getName() + " to the subdirectory of " + dir.getPath();
			logger.error(msg);
		}
		return result;
	}
	
	
    /** Copy a file from one filename to another */
    public static void copyFile(String inName, String outName)    throws FileNotFoundException, IOException {
        BufferedInputStream is = 
            new BufferedInputStream(new FileInputStream(inName));
        BufferedOutputStream os = 
            new BufferedOutputStream(new FileOutputStream(outName));
        copyFile(is, os, true);
    }

    /** Copy a file from an opened InputStream to opened OutputStream */
    public static void copyFile(InputStream is, OutputStream os, boolean close)     throws IOException {
        int b;              
        // the byte read from the file
        while ((b = is.read()) != -1) {
            os.write(b);
        }
        is.close();
        if (close)
            os.close();
    }   
    
    public static void gzipFile(String from, String to) throws IOException {
	    FileInputStream in = new FileInputStream(from);
	    GZIPOutputStream out = new GZIPOutputStream(new FileOutputStream(to));
	    byte[] buffer = new byte[4096];
	    int bytesRead;
	    while ((bytesRead = in.read(buffer)) != -1)
	      out.write(buffer, 0, bytesRead);
	    in.close();
	    out.close();
	  }

	  /** Zip the contents of the directory, and save it in the zipfile */
	  public static void zipDirectory(String dir, String zipfile)
	      throws IOException, IllegalArgumentException {
	    // Check that the directory is a directory, and get its contents
	    File d = new File(dir);
	    
	    if (!d.isDirectory())
	      throw new IllegalArgumentException("Not a directory:  "  + dir);
	    
	    String[] entries = d.list();
	    byte[] buffer = new byte[4096]; // Create a buffer for copying
	    int bytesRead;

	    ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipfile));

	    for (int i = 0; i < entries.length; i++) {
	      File f = new File(d, entries[i]);
	      if (f.isDirectory())
	        continue;//Ignore directory
	      FileInputStream in = new FileInputStream(f); // Stream to read file
	     // ZipEntry entry = new ZipEntry(f.getPath()); // Make a ZipEntry
	      ZipEntry entry = new ZipEntry(f.getName()); 
	      out.putNextEntry(entry); // Store entry
	      while ((bytesRead = in.read(buffer)) != -1)
	        out.write(buffer, 0, bytesRead);
	      in.close(); 
	    }
	    out.close();
	  }
	  
	  public static List<String> readFileByReader(String path) throws Exception{
		  BufferedReader br = null;
		  ArrayList<String> arrayList = new ArrayList<String>();
			try {
				
				String sCurrentLine;
	 
				br = new BufferedReader(new FileReader(path));
	 
				while ((sCurrentLine = br.readLine()) != null) {
					arrayList.add(sCurrentLine);
				}
				
			} catch (IOException e) {
				
			} finally {
				try {
					if (br != null)br.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
			return arrayList;
	  }
	  
	  public static void composeFolderPath(String fullPath) {
			
			File dir = new File(fullPath);
		    dir = new File(fullPath);
		    if(!dir.exists()) {
		    	dir.mkdir();
		    }	    	       
			   
		}
	  
	  public static void deleteFolder(String fullPath) {
			
			File dir = new File(fullPath);
		    if(dir.exists()) {
		    	dir.delete();
		    	dir.mkdir();
		    }	    	       
			   
		}
	  
	  public static void deleteFile(String fullPath) {
			
			File file = new File(fullPath);
		    if(file.exists()) {
		    	file.delete();
		    }	    	       
			   
		} 
	  
		public static byte[] getByteArrayForFile(String fileName){
			File f = new File(fileName);
			byte[] result = null;
			
			if(f.exists() == false) {
				logger.error("File not exist to view ..." + fileName);				
			}
			InputStream is = null;
			try {
				is = new FileInputStream(f);
				result = new byte[is.available()];
				is.read(result);
			} catch(Exception e) {
				logger.error("Exception opening file", e);
			} finally {
				if(is != null) { 
					try {
						is.close();
					} catch (Exception ex) {
						logger.error("File not exist to view ..." + fileName);
					}
				}
			}
			return result;
		}
		
		public static void listFolderExcludeSub(String path){
			  
			  File myFile = new File(path);
			  if (myFile.isDirectory()){
			    for(File s: myFile.listFiles()){
			      if (s.isDirectory()){
			    	  logger.error("Folder:"+s);
			      } else {
			    	  logger.error("File:"+s);
			      }		    	  
			    }  
			  }
			    
		  }
		  
		  public static void listFolderIncludeSub(String path){
			  
			  
			  File files[]; 
			  indentLevel++; 

			  files = new File(path).listFiles();

		      Arrays.sort(files);
			  for (int i = 0, n = files.length; i < n; i++) {
			      for (int indent = 0; indent < indentLevel; indent++) {
			        System.out.print("  ");
			      }
			      logger.error(files[i].toString());
			      if (files[i].isDirectory()) {

			    	  listFolderIncludeSub(files[i].getAbsolutePath());
			      }
			    }
			    indentLevel--; 
			    
		  }
		  
		  public static void showFileDetails(String path){
			  File f1 = new File(path);
			  logger.error("File Name:" + f1.getName());
			  logger.error("Path:" + f1.getPath());
			  logger.error("Abs Path:" + f1.getAbsolutePath());
			  logger.error("Parent:" + f1.getParent());
			  logger.error(f1.exists() ? "exists" : "does not exist");
			  logger.error(f1.canWrite() ? "is writeable" : "is not writeable");
			  logger.error(f1.canRead() ? "is readable" : "is not readable");
			  logger.error("is a directory" + f1.isDirectory() );
			  logger.error(f1.isFile() ? "is normal file" : "might be a named pipe");
			  logger.error(f1.isAbsolute() ? "is absolute" : "is not absolute");
			  logger.error("File last modified:" + f1.lastModified());
			  logger.error("File size:" + f1.length() + " Bytes");

		  }
		  
		  public static void readFileByStream(String path){
			  File file = new File(path);
				FileInputStream fis = null;
		 
				try {
					fis = new FileInputStream(file);
		 
					logger.error("Total file size to read (in bytes) : "+ fis.available());
		 
					int content;
					while ((content = fis.read()) != -1) {
						// convert to char and display it
						System.out.print((char) content);
					}
		 
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						if (fis != null)
							fis.close();
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				}
		  }
		  


}