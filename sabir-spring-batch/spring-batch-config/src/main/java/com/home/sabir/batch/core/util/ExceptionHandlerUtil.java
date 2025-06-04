package com.home.sabir.batch.core.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ExceptionHandlerUtil {
	
	private static final Logger logger = LogManager.getLogger(ExceptionHandlerUtil.class);

	public static void handleBatchExeption(Exception e) {
		
		boolean serializeError = false;
		try {
			logger.error("SABIR SPRING Batch Failed due to "+e.getMessage(),e);
			if (e.getCause()!=null && e.getCause().getMessage()!=null && e.getCause().getMessage().indexOf("ORA-08177")>0){					
					serializeError = true;
			}
			if (serializeError==true){
				logger.info("===========Job Failed due to ORA-08177 Exception with exit code 2");
				System.exit(2);
			}else{
				logger.info("===========Job Failed due to Exception with exit code 1");
				System.exit(1);
			};
		} catch (Exception e1) {
			logger.info("===========Job Failed due to Exception with exit code 1");
			System.exit(1);
		}
	}
}
