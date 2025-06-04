package com.home.sabir.batch.common.task;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;


public class COMMON_A_P_Truncate_STG_Table_Task implements Tasklet {
	

		private static final Logger logger = LogManager.getLogger(COMMON_A_P_Truncate_STG_Table_Task.class);
	
		private DataSource dataSource;
	
	    private String sql;
	
		public COMMON_A_P_Truncate_STG_Table_Task(String sql,DataSource dataSource) {
			this.sql = sql;
	        this.dataSource = dataSource;
		}
		 
	    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
	    		    	
	        String tableName = (String) chunkContext.getStepContext().getJobParameters().get("tableName");

	        logger.info("Truncation Table............."+tableName);
	        JdbcTemplate jdbcTemplate= new JdbcTemplate(dataSource);
	        jdbcTemplate.execute(sql, new PreparedStatementCallback<Boolean>(){
	            @Override  
	            public Boolean doInPreparedStatement(PreparedStatement ps)  
	                    throws SQLException, DataAccessException {
	                
	            	ps.setString(1, tableName);
	            	return ps.execute();  
	                      
	            }  
	            });  
	        
	        return RepeatStatus.FINISHED;
	    }   
	}