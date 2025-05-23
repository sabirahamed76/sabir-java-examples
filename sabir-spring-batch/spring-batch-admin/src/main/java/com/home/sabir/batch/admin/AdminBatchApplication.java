package com.home.sabir.batch.admin;

import java.util.StringTokenizer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.home.sabir.batch.core.util.ExceptionHandlerUtil;

@SpringBootApplication 
public class AdminBatchApplication implements CommandLineRunner {
	
	@Autowired
	private JobRegistry jobRegistry;

	@Autowired
	JobLauncher jobLauncher;
	
	@Value("${jobName:BatchConfigTest}")
	String jobName;

	@Value("${params}")
	String params;

	private static String exitCode;
	
	
	private static final Logger logger = LogManager.getLogger(AdminBatchApplication.class);

	public static void main(String[] args) {
		logger.info("Batch Process Started");
 		try{
 			SpringApplication.run(AdminBatchApplication.class, args);

	 		
	 		if ("COMPLETED".equals(exitCode) ) {
	 			logger.info("===========Job Completed Successfully with exit code 0");
	 			System.exit(0);
	 		}else {
	 			logger.info("===========Job Failed with exit code 1");
	 			System.exit(1);
	 		}
		} catch (Exception e) {			
			ExceptionHandlerUtil.handleBatchExeption(e);
	 	}
	}

	@Override
	public void run(String... args) throws Exception {
		
		
		JobParametersBuilder jpBuilder = new JobParametersBuilder();
		jpBuilder.addString("JobID", String.valueOf(System.currentTimeMillis()));
		
		logger.info("************************************* job name : " + jobName);
		logger.info("************************************* params : " + params );
		if (params!=null && !params.equals("")) {
			StringTokenizer st = new StringTokenizer (params,",");
			while(st.hasMoreTokens()) {
				try {
					String s= st.nextToken();
					String key;
					String val;			
					key = s.substring(0, s.indexOf(':'));
					val = s.substring(s.indexOf(':') + 1, s.length());
					jpBuilder.addString(key, val);
				} catch (Exception e) {
					logger.info("Exception while iterating parameters...");
				}			
			}
		}
		Job job = jobRegistry.getJob(jobName);
		

		JobExecution jobExecution = jobLauncher.run(job, jpBuilder.toJobParameters());
		
		logger.info(jobExecution.getExitStatus().getExitDescription());

		exitCode = jobExecution.getExitStatus().getExitCode();
		
	}
}
