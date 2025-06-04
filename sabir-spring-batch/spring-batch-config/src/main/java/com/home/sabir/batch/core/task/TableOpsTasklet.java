package com.home.sabir.batch.core.task;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;

public class TableOpsTasklet implements Tasklet {

	 private DataSource dataSource;

    private String sql;
    

    public TableOpsTasklet (String sql, DataSource dataSource) {
        this.sql = sql;
        this.dataSource = dataSource;
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

         if (!sql.equals("")) {
	        JdbcTemplate jdbcTemplate= new JdbcTemplate(dataSource);
	        jdbcTemplate.execute(sql, new PreparedStatementCallback<Boolean>(){
	            @Override  
	            public Boolean doInPreparedStatement(PreparedStatement ps)  
	                    throws SQLException, DataAccessException {
                      
	                return ps.execute();  
	                      
	            }  
	            });  
         }
         return RepeatStatus.FINISHED;
    }

}