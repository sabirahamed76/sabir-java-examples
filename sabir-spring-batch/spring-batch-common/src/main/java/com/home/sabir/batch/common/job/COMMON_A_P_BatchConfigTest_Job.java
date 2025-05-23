package com.home.sabir.batch.common.job;

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

import com.home.sabir.batch.core.task.TableOpsTasklet;

@Configuration
@Lazy
public class COMMON_A_P_BatchConfigTest_Job {
	
	private static final Logger logger = LogManager.getLogger(COMMON_A_P_BatchConfigTest_Job.class);

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private DataSource dataSource;
	
	String batchJobIdSql = "SELECT BATCH_JOB_ID FROM BATCH_JOB WHERE BATCH_JOB_NAME='SYSTEM'";	
	

	

	
	@Bean
	public Job COMMON_A_P_BatchConfigTest() {
		logger.info("BatchConfigTest_Job started");
		return jobBuilderFactory.get("COMMON_A_P_BatchConfigTest")
				.incrementer(new RunIdIncrementer())
				.start(BatchConfigTest_Step()) 
				.build();
	}
	
	@Bean
	public Step BatchConfigTest_Step(){
		logger.info("BatchConfigTest_Job started");
		return stepBuilderFactory.get("BatchConfigTest_Step")
				.tasklet(tasklet(batchJobIdSql))
				.build();       
	}

	public Tasklet tasklet(String sql) {
		return new TableOpsTasklet(batchJobIdSql, dataSource);
	}
}
