package com.home.sabir.batch.ibis.job;

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

import com.home.sabir.batch.core.model.BatchParams;
import com.home.sabir.batch.core.task.TableOpsTasklet;
import com.home.sabir.batch.ibis.service.IBIS_D_P_SEP_Caye_Rate_Info_Service;
import com.home.sabir.batch.ibis.task.IBIS_D_P_SEP_Caye_Rate_Update_Task;

@Configuration
@Lazy
public class IBIS_D_P_SEP_Caye_Rate_Update_Job {

	private static final Logger logger = LogManager.getLogger(IBIS_D_P_SEP_Caye_Rate_Update_Job.class);

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private DataSource dataSource;

	@Autowired
	private BatchParams batchParams;

	@Autowired
	private IBIS_D_P_SEP_Caye_Rate_Info_Service cayeRateInfoService;

	@Bean
	public Job IBIS_D_P_SEP_Caye_Rate_Update() {
		logger.info("IBIS_D_P_SEP_Caye_Rate_Update Started");
		return jobBuilderFactory.get("IBIS_D_P_SEP_Caye_Rate_Update").incrementer(new RunIdIncrementer())
				.start(SEPCayeRateUpdateStep())
				.build();
	}

	@Bean
	public Step SEPCayeRateUpdateStep() {
		logger.info("SEPCayeRateUpdateStep Started");
		String transDate = (String) batchParams.get("transDate");
		return stepBuilderFactory.get("SEP_D_P_Caye_Rate_Update_Step")
				.tasklet(new IBIS_D_P_SEP_Caye_Rate_Update_Task(dataSource, cayeRateInfoService, transDate)).build();
	}

	public Tasklet tasklet(String sql) {
		return new TableOpsTasklet(sql, dataSource);
	}

}
