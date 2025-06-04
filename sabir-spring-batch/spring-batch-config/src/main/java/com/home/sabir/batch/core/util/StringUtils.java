package com.home.sabir.batch.core.util;

import java.io.FileReader;
import java.io.LineNumberReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StringUtils {
	
	private static final Logger logger = LogManager.getLogger(StringUtils.class);
	
	public static String fillString(String padType,int length,String str,String fillType) {
		String output="";
		if(str==null)str="";
			if (str.length()==length)
				return str;
			for (int i=0;i<length-str.length();i++) {
				output +=fillType;
			}
			if ("left".equals(padType)){
				output = output+str;			
			}else if ("right".equals(padType)){
				output = str+output;
			}	
		return output;
	}
	
	public static boolean isValid(String obj) {
		try {
			if(null == obj)
				return false;
			if("".equals(obj.trim()))
				return false;
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	public static Long getNoOfLines(String fileName) {
		Long linenumber = 0L;
		try {
			FileReader reader = new FileReader(fileName);
			LineNumberReader lnr = new LineNumberReader(reader);
			while (lnr.readLine() != null){
				linenumber++;
			}
			lnr.close();
		} catch (Exception e) {
			logger.error("File not found in path: " + fileName);
		}
		return linenumber;
	}
}
