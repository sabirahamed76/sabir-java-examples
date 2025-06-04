package com.home.sabir.batch.admin;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.support.ApplicationContextFactory;
import org.springframework.batch.core.configuration.support.GenericApplicationContextFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

import com.home.sabir.batch.JobBatchConfigurer;
import com.home.sabir.batch.admin.job.ADMIN_D_P_Comp_Email_Notify_Job;
import com.home.sabir.batch.admin.job.ADMIN_D_P_SEP_Caye_Rate_Update_Job;
import com.home.sabir.batch.admin.job.ADMIN_Email_Test_Job;

@Configuration
@PropertySource({ "batch.properties", "application.properties", "file:${confPath}/datasource.properties", "file:${confPath}/enets.properties", "file:${confPath}/email.properties" })
@Import(JobBatchConfigurer.class)
@EnableBatchProcessing(modular = true)
public class AdminBatchConfiguration {

	@Value("${jobName}")
	String jobName;

	@Bean
	public ApplicationContextFactory applicationContextFactory() {
		switch (jobName) {
		/*********************  Jobs *************************/
			

		case "ADMIN_D_P_SEP_Caye_Rate_Update":
			return new GenericApplicationContextFactory(ADMIN_D_P_SEP_Caye_Rate_Update_Job.class);
			
		case "ADMIN_D_P_Comp_Email_Notify":
			return new GenericApplicationContextFactory(ADMIN_D_P_Comp_Email_Notify_Job.class);

		case "ADMIN_Email_Test":
			return new GenericApplicationContextFactory(ADMIN_Email_Test_Job.class);

		default:
			return null;
		}

	}

}
