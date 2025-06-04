package com.home.java.utils.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;

/**
 *   File Utility Class
 */
public class FileDownloadUtil
{
	private static final Logger logger = Logger.getLogger(FileDownloadUtil.class);
	
	  public static FileDownloadFormDto downloadFile(String fileName) {

			if(fileName == null) {
				return null;
			}

			File f = new File(fileName);
			if(f.exists() == false) {
				logger.error("File not exist to view ..." + fileName);
				return null;
			}
			
			FileDownloadFormDto result = new FileDownloadFormDto();
			result.setFileName(FilenameUtils.getName(fileName));		
			
			InputStream is = null;
			try {
				is = new FileInputStream(f);
				int dataLen = is.available();
				byte[] data = new byte[dataLen];
				is.read(data);			
				result.setData(data);
				result.setDataLen(dataLen);
			} catch(Exception e) {
				logger.error("Exception opening file", e);
			} finally {
				if(is != null) { 
					try {
						is.close();
					} catch (Exception ex) {
						
					}
				}
			}
			return result;
		}
}