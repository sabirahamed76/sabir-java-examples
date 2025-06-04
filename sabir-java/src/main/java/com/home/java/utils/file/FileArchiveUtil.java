package com.home.java.utils.file;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;

/**
 *
 */
public class FileArchiveUtil
{
	
	private static final Logger logger = Logger.getLogger(FileArchiveUtil.class);
// -------------------------- STATIC METHODS --------------------------

    public static void archiveFile(File fileToProcess, String archiveDirectory, boolean removeTempExt) throws IOException
    {      
        
        if (fileToProcess.exists())
        {
        	
        	String fileName = fileToProcess.getName();
        	String fileNameWithoutTempExt = null;
        	if(removeTempExt){
        	int endIndex = fileName.length();             
        	// Find the delimiter    		
    		 fileNameWithoutTempExt = fileName.substring(0,endIndex - 4);
        	}else
        		fileNameWithoutTempExt = fileName;
        	//create a copy of the oringal file in the archive location and delete the orginal
            File outputFile = new File(archiveDirectory + fileNameWithoutTempExt);
            
   
           FileUtil.copyFile(fileToProcess.getAbsolutePath(),archiveDirectory + fileNameWithoutTempExt);          
              
            //Finally check the archive file exists, is the same size as the original and isn't the SAME file, if OK delete the original
            if (outputFile.exists() && (outputFile.length() == fileToProcess.length()) && (outputFile.getAbsolutePath().compareTo(fileToProcess.getAbsolutePath()) != 0)) {
                fileToProcess.delete();
            } 
        } else {
            logger.error("ServiceFileHelper, file does not exist " + fileToProcess.getAbsolutePath());
        }
    }
    


}
