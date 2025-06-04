package com.home.sabir.batch.common;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.support.ApplicationContextFactory;
import org.springframework.batch.core.configuration.support.GenericApplicationContextFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

import com.home.sabir.batch.JobBatchConfigurer;
import com.home.sabir.batch.common.job.COMMON_A_P_BatchConfigTest_Job;
import com.home.sabir.batch.common.job.COMMON_A_P_Count_Records_Table_Job;
import com.home.sabir.batch.common.job.COMMON_A_P_Select_Query_Job;
import com.home.sabir.batch.common.job.COMMON_A_P_Truncate_Stage_Table_Job;
import com.home.sabir.batch.common.job.COMMON_A_P_Email_Test_Job;

@Configuration
@PropertySource({ "batch.properties", "application.properties", "file:${confPath}/datasource.properties", "file:${confPath}/enets.properties", "file:${confPath}/email.properties" })
@Import(JobBatchConfigurer.class)
@EnableBatchProcessing(modular = true)
public class CommonBatchConfiguration {

	@Value("${jobName}")
	String jobName;

	@Bean
	public ApplicationContextFactory applicationContextFactory() {
		switch (jobName) {
		/*********************  Jobs *************************/
		case "COMMON_A_P_BatchConfigTest":
			   return new GenericApplicationContextFactory(COMMON_A_P_BatchConfigTest_Job.class);
			   
		case "COMMON_A_P_Truncate_Stage_Table":
			   return new GenericApplicationContextFactory(COMMON_A_P_Truncate_Stage_Table_Job.class);			   
			
		case "COMMON_A_P_Count_Records_Table":
			   return new GenericApplicationContextFactory(COMMON_A_P_Count_Records_Table_Job.class);

		case "COMMON_A_P_SELECT_QUERY":
			return new GenericApplicationContextFactory(COMMON_A_P_Select_Query_Job.class);		
			
		case "COMMON_A_P_Email_Test":
			return new GenericApplicationContextFactory(COMMON_A_P_Email_Test_Job.class);
			
			

		default:
			return null;
		}

	}

}
