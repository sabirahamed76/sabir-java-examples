package com.home.sabir.batch.common.job;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import com.home.sabir.batch.common.task.COMMON_A_P_Count_Records_Table_Task;

@Configuration
@Lazy
public class COMMON_A_P_Count_Records_Table_Job {

		
		private static final Logger logger = LogManager.getLogger(COMMON_A_P_Count_Records_Table_Job.class);
		
		@Autowired
		private JobBuilderFactory jobBuilderFactory;

		@Autowired
		private StepBuilderFactory stepBuilderFactory;
		
		@Autowired
		private DataSource dataSource;
		
		
		@Bean
		public Job COMMON_A_P_Count_Records_Table() {			
			logger.info("COMMON_A_P_Count_Records_Table Started");
			return jobBuilderFactory.get("COMMON_A_P_Count_Records_Table")
					.incrementer(new RunIdIncrementer())
					.start(Count_Records_Table_Step()) 
					.build();
		}
		
		@Bean
	    public Step Count_Records_Table_Step(){
			
			 return stepBuilderFactory.get("Count_Records_Table_Step")
		        		.tasklet(new COMMON_A_P_Count_Records_Table_Task(dataSource)) 
		                .build();      
		}
	
		
}
