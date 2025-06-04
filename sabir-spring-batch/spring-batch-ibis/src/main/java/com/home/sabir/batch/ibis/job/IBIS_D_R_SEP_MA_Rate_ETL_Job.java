package com.home.sabir.batch.ibis.job;

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

import com.home.sabir.batch.ibis.service.IBIS_D_R_SEP_MA_Rate_Service;
import com.home.sabir.batch.ibis.task.IBIS_D_R_SEP_MA_Rate_Step_Task;

@Configuration
@Lazy
public class IBIS_D_R_SEP_MA_Rate_ETL_Job {
	
	private static final Logger logger = LogManager.getLogger(IBIS_D_R_SEP_MA_Rate_ETL_Job.class);

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private IBIS_D_R_SEP_MA_Rate_Service calCayeConRateService;
	
	
	

	@Bean
	public Job IBIS_D_R_SEP_MA_Rate_ETL() {
		logger.info("IBIS_D_R_SEP_MA_Rate_ETL started");
		return jobBuilderFactory.get("IBIS_D_R_SEP_MA_Rate_ETL")
				.incrementer(new RunIdIncrementer())
				.start(IBIS_D_R_SEP_MA_Rate_Step()) 		//INSERT INTO SEP_IRAS_TXN TABLE
				.build();
	}
	
	@Bean
	public Step IBIS_D_R_SEP_MA_Rate_Step(){
		return stepBuilderFactory.get("IBIS_D_R_SEP_MA_Rate_Step")
        		.tasklet(new IBIS_D_R_SEP_MA_Rate_Step_Task(dataSource, calCayeConRateService))
				.build();       
	}

	
}
