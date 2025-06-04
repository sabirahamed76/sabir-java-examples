package com.home.sabir.batch.common.task;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;

import com.home.sabir.batch.core.util.StringUtils;


public class COMMON_A_P_Count_Records_Table_Task implements Tasklet {
	

		private static final Logger logger = LogManager.getLogger(COMMON_A_P_Count_Records_Table_Task.class);
	
		private DataSource dataSource;
	
	    private String sql;
	
		public COMMON_A_P_Count_Records_Table_Task(DataSource dataSource) {
			
	        this.dataSource = dataSource;
		}
		 
	    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

			String sql = "select count(1) from  ";
				    	
	        String tableName = (String) chunkContext.getStepContext().getJobParameters().get("tableName");

			if (!StringUtils.isValid(tableName))
				tableName="BATCH_JOB";
			sql = sql + tableName;
			
	        logger.info("******************Counting of  Table............."+tableName);
	        JdbcTemplate jdbcTemplate= new JdbcTemplate(dataSource);
	        
	        
	        List<String> count = jdbcTemplate.queryForList(sql, String.class);
	        
	        logger.info("******************Count of the " + tableName + " is = "+ count);


	        
	        return RepeatStatus.FINISHED;
	    }   
	}