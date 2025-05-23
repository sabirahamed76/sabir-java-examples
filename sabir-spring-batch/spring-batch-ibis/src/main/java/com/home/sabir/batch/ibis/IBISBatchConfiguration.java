package com.home.sabir.batch.ibis;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.support.ApplicationContextFactory;
import org.springframework.batch.core.configuration.support.GenericApplicationContextFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

import com.home.sabir.batch.JobBatchConfigurer;
import com.home.sabir.batch.ibis.job.IBIS_D_P_Comp_Email_Notify_Job;
import com.home.sabir.batch.ibis.job.IBIS_D_P_SEP_Caye_Rate_Update_Job;
import com.home.sabir.batch.ibis.job.IBIS_D_R_SEP_Info_Ack_FileLoadStg_Job;
import com.home.sabir.batch.ibis.job.IBIS_D_R_SEP_MA_Rate_ETL_Job;
import com.home.sabir.batch.ibis.job.IBIS_D_R_SEP_MA_Rate_FileLoadStg_Job;
import com.home.sabir.batch.ibis.job.IBIS_D_R_SEP_MA_Rate_FileValidate_Job;
import com.home.sabir.batch.ibis.job.IBIS_D_S_SEP_Info_ETL_Job;
import com.home.sabir.batch.ibis.job.IBIS_D_S_SEP_Info_FileCreate_Job;

@Configuration
@PropertySource({ "batch.properties", "application.properties", "file:${confPath}/datasource.properties", "file:${confPath}/enets.properties", "file:${confPath}/email.properties" })
@Import(JobBatchConfigurer.class)
@EnableBatchProcessing(modular = true)
public class IBISBatchConfiguration {

	@Value("${jobName}")
	String jobName;

	@Bean
	public ApplicationContextFactory applicationContextFactory() {
		switch (jobName) {
		/*********************  Jobs *************************/
			
		case "IBIS_D_S_SEP_Info_ETL":
			return new GenericApplicationContextFactory(IBIS_D_S_SEP_Info_ETL_Job.class);
			
		case "IBIS_D_S_SEP_Info_FileCreate":
			return new GenericApplicationContextFactory(IBIS_D_S_SEP_Info_FileCreate_Job.class);
			
		case "IBIS_D_R_SEP_Info_Ack_FileLoadStg":
			return new GenericApplicationContextFactory(IBIS_D_R_SEP_Info_Ack_FileLoadStg_Job.class);	
			
			
		case "IBIS_D_R_SEP_MA_Rate_FileValidate":
			return new GenericApplicationContextFactory(IBIS_D_R_SEP_MA_Rate_FileValidate_Job.class);
			
		case "IBIS_D_R_SEP_MA_Rate_FileLoadStg":
			return new GenericApplicationContextFactory(IBIS_D_R_SEP_MA_Rate_FileLoadStg_Job.class);
			
		case "IBIS_D_R_SEP_MA_Rate_ETL":
			return new GenericApplicationContextFactory(IBIS_D_R_SEP_MA_Rate_ETL_Job.class);
			
		case "IBIS_D_P_Comp_Email_Notify":
			return new GenericApplicationContextFactory(IBIS_D_P_Comp_Email_Notify_Job.class);
			
		case "IBIS_D_P_SEP_Caye_Rate_Update":
			return new GenericApplicationContextFactory(IBIS_D_P_SEP_Caye_Rate_Update_Job.class);
			
		default:
			return null;
		}

	}

}
