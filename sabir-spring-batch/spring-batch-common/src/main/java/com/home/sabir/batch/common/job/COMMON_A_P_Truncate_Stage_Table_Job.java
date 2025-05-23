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

import com.home.sabir.batch.common.task.COMMON_A_P_Truncate_STG_Table_Task;

@Configuration
@Lazy
public class COMMON_A_P_Truncate_Stage_Table_Job {

		
		private static final Logger logger = LogManager.getLogger(COMMON_A_P_Truncate_Stage_Table_Job.class);
		
		@Autowired
		private JobBuilderFactory jobBuilderFactory;

		@Autowired
		private StepBuilderFactory stepBuilderFactory;
		
		@Autowired
		private DataSource dataSource;
		
		private String trncateStgTableSql = "CALL PRC_TRUNCATE_TABLE(?)";
		

		
		@Bean
		public Job COMMON_A_P_Truncate_Stage_Table() {			
			logger.info("COMMON_A_P_Truncate_Stage_Table Started");
			return jobBuilderFactory.get("COMMON_A_P_Truncate_Stage_Table")
					.incrementer(new RunIdIncrementer())
					.start(Truncate_STG_Table_Step()) 
					.build();
		}
		
		@Bean
	    public Step Truncate_STG_Table_Step(){
	        return stepBuilderFactory.get("Truncate_STG_Table_Step")
	        		.tasklet(new COMMON_A_P_Truncate_STG_Table_Task(trncateStgTableSql,dataSource)) 
	                .build();       
	    }
		
}
