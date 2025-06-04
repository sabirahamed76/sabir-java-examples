package com.home.java.utils.property;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.home.java.utils.file.FileUtil;

public class PropertyLoader {
 private static final Logger logger = Logger.getLogger(PropertyLoader.class);	
 private static final String ENV_VARIABLE = "itoEnv";
 private static Properties props = null;
 
 public static Properties loadProperties(){
	 if(props == null){
		 InputStream in = null;
		 try {
			 props = new Properties();
			 in=FileUtil.getStreamFromClassPathOrFileSystem(System.getProperty(ENV_VARIABLE)+".properties");
			 try{
			 props.load(in);
			 }finally{				 
				 in.close();
			 }
		 } catch (IOException e) {
			logger.error(e);
		 }
	 }
	 return props;
 }
	
}
