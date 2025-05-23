package com.home.sabir.batch.core.task;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.home.sabir.batch.core.model.BatchJobExceptionModel;
import com.home.sabir.batch.core.util.StringUtils;

public class BatchJobErrorLogsTasklet implements Tasklet {

	private static final Logger logger = LogManager.getLogger(BatchJobErrorLogsTasklet.class);

	private DataSource dataSource;

	public static boolean canBeProcessed = false;

	private String batchJobName;
	private String startDateTime;
	private String tableName;

	public BatchJobErrorLogsTasklet (DataSource dataSource, String batchJobName, String startDateTime,String tableName) {
		this.dataSource = dataSource;
		this.batchJobName = batchJobName;
		this.startDateTime = startDateTime;
		this.tableName = tableName;
	}

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		logger.info("*****BatchJobErrorLogsTasklet started*****");
		logger.info("value of batchJobName - " + batchJobName + " and startDateTime - " + startDateTime);
		List<BatchJobExceptionModel> errorList = new ArrayList<>();
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("batchJobName", batchJobName);
		params.addValue("startDateTime", startDateTime);
		String batchJobErrorsSql = 
				"SELECT DISTINCT LINE_NBR, COLUMN_NAME, COLUMN_VALUE, REJECTION_TYPE, REMARKS, TO_CHAR(EXECUTION_DATE, 'DD-MON-YY') AS EXECUTION_DATE, "
						+ "REF_KEY_NAMES, REF_KEY_VALUES "
				+ "FROM "+tableName+" WHERE BATCH_JOB_ID = (SELECT BATCH_JOB_ID FROM BATCH_JOB WHERE BATCH_JOB_NAME = :batchJobName) "
						+ "AND CREATED_TS BETWEEN :startDateTime AND CURRENT_TIMESTAMP "
				+ " ORDER BY LINE_NBR";

		new NamedParameterJdbcTemplate(dataSource).query(batchJobErrorsSql, params, new ResultSetExtractor<List<BatchJobExceptionModel>>() {
			@Override
			public List<BatchJobExceptionModel> extractData(ResultSet rs) throws SQLException, DataAccessException {
				int count = 0;
				while(rs.next()) {
					BatchJobExceptionModel mdl = new BatchJobExceptionModel();
					count++;

					if(count == 1) {
						if(null != rs.getString("EXECUTION_DATE") && !rs.getString("EXECUTION_DATE").trim().equals(""))
							mdl.setEXECUTION_DATE_STR(rs.getString("EXECUTION_DATE"));
					}
					if(null != rs.getString("LINE_NBR") && !rs.getString("LINE_NBR").trim().equals(""))
						mdl.setLINE_NBR(Long.valueOf(rs.getString("LINE_NBR").trim()));
					if(null != rs.getString("REMARKS") && !rs.getString("REMARKS").trim().equals(""))
						mdl.setREMARKS(rs.getString("REMARKS").trim());
					errorList.add(mdl);
				}
				return errorList;
			}
		});

		if(!errorList.isEmpty()) {
			logger.info("Size of errorList fetched - " + errorList.size());

			logger.info("BATCH_JOB_NAME: " + batchJobName);
			logger.info("EXECUTION_DATE: " + errorList.get(0).getEXECUTION_DATE_STR());
			
			String separator = org.apache.commons.lang3.StringUtils.center("|", 3);

			logger.info(StringUtils.fillString("right", 20, "LINE_NBR", " ") + separator
			+ StringUtils.fillString("right", 1000, "REMARKS", " ") + separator);
			logger.info(String.format("%s", "------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------"));
			
			for(BatchJobExceptionModel model : errorList) {
				
				logger.info(StringUtils.fillString("right", 20, null != model.getLINE_NBR() ? model.getLINE_NBR().toString() : "", " ") + separator
					+ StringUtils.fillString("right", 1000,  null != model.getREMARKS() ? model.getREMARKS() : "", " ") + separator);
			}
		} else
			logger.info("No validation errors found for the job: " + batchJobName);

		logger.info("*****BatchJobErrorLogsTasklet ended*****");
		return RepeatStatus.FINISHED;
	}
	
	/*private String maskNRIC(String nric) {
		String maskNRIC = "";
		if (org.apache.commons.lang3.StringUtils.isNotBlank(nric)) {
			if(nric.length() <= 4)
				maskNRIC = org.apache.commons.lang3.StringUtils.repeat("X", nric.length());
			else
				maskNRIC = org.apache.commons.lang3.StringUtils.repeat("X", 4) + nric.substring(4, nric.length());
		}		
		logger.info("maskNRIC - "  + maskNRIC);		
		return maskNRIC;
	}*/
}