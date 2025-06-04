package com.home.sabir.batch.common.job;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;

import com.home.sabir.batch.core.model.BatchParams;
import com.home.sabir.batch.core.task.AdhocQueryTasklet;

@Configuration
@Lazy
public class COMMON_A_P_Select_Query_Job {
	
	private static final Logger logger = LogManager.getLogger(COMMON_A_P_Select_Query_Job.class);

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private BatchParams batchParams;
	
	@Autowired
	private Environment env;
	
	private String filePath ;
//	private String inFileName ;
//	private String outFileName ;
	
	
	@PostConstruct
	public void printQuery() {
		filePath = env.getProperty("adhoc.job.steps.filepath");
		
//		inFileName = env.getProperty("COMMON_A_P_QUERY_IN");		
//		outFileName = env.getProperty("COMMON_A_P_QUERY_OUT");		
		logger.info("filePath  " +  filePath);
//		logger.info("filePath  " +  inFileName);
//		logger.info("filePath  " +  outFileName);
	}
	
	@Bean
	public Job COMMON_A_P_SELECT_QUERY() {
		logger.info("COMMON_A_P_QUERY_JOB started");
		return jobBuilderFactory.get("COMMON_A_P_SELECT_QUERY")
				.incrementer(new RunIdIncrementer())
				.start(COMMON_A_P_QUERY_Step()) 
				.build();
	}
	
	@Bean
	public Step COMMON_A_P_QUERY_Step(){
		logger.info("COMMON_A_P_QUERY_Step started");
		return stepBuilderFactory.get("COMMON_A_P_QUERY_Step")
				.tasklet(tasklet())
				.build();       
	}

	public Tasklet tasklet() {
		return new AdhocQueryTasklet(dataSource, filePath);
	}
}
