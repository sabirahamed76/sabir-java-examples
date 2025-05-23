package com.home.sabir.batch.core.task;

import java.util.List;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.jdbc.core.JdbcTemplate;

public class TableHolidayTasklet implements Tasklet {
	
	private static final Logger logger = LogManager.getLogger(TableHolidayTasklet.class);

	private DataSource dataSource;

	public static boolean canBeProcessed = false;

	public TableHolidayTasklet (DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		logger.info("*****TableHolidayTasklet started*****");
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		//		String transDate = (String) chunkContext.getStepContext().getJobParameters().get("transDate");
		//		logger.info("value of transDate - " + transDate);		
		List<String> holiday = jdbcTemplate.queryForList("SELECT 'Y' FROM TB_CPP_HDY WHERE HDY_DTE = TRUNC(CURRENT_DATE)", String.class);
		if(holiday.isEmpty()) {
			logger.info("Today is not a holiday..TableHolidayTasklet.canBeProcessed - " + TableHolidayTasklet.canBeProcessed);
			TableHolidayTasklet.canBeProcessed = true;
		} else 
			logger.info("Today is a holiday..TableHolidayTasklet.canBeProcessed - " + TableHolidayTasklet.canBeProcessed);			

		logger.info("*****TableHolidayTasklet ended*****");
		return RepeatStatus.FINISHED;
	}
}