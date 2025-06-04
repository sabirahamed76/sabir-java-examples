package com.home.sabir.batch.ibis.task;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.jdbc.core.JdbcTemplate;

import com.home.sabir.batch.ibis.service.IBIS_D_R_SEP_MA_Rate_Extractor_Service;
import com.home.sabir.batch.ibis.service.IBIS_D_R_SEP_MA_Rate_Service;

public class IBIS_D_R_SEP_MA_Rate_Step_Task implements Tasklet {
	
		private DataSource dataSource;
		private static final Logger logger = LogManager.getLogger(IBIS_D_R_SEP_MA_Rate_Step_Task.class);
		private IBIS_D_R_SEP_MA_Rate_Service semyrSepMARateService;

		public IBIS_D_R_SEP_MA_Rate_Step_Task(DataSource dataSource, IBIS_D_R_SEP_MA_Rate_Service semyrSepMARateService) {
	        this.dataSource = dataSource;
	        this.semyrSepMARateService = semyrSepMARateService;
		}
		 
	    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
	    	JdbcTemplate jdbcTemplate= new JdbcTemplate(dataSource);
	        String relYear = (String) chunkContext.getStepContext().getJobParameters().get("relYear");
	        	        
	        String sepSelectSql =  "SELECT DISTINCT SEP_IRAS_INFO.SEP_IRAS_INFO_ID, SEP_INFO.SEP_INFO_ID, SEP_IRAS_INFO.NRIC, SEP_IRAS_INFO.RELEVANT_YEAR, APPL_YEAR, " +
	        		"APPL_MA_CON_RATE AS MA_CON_RATE, MAX_APPL_MA_CON_RATE AS MAX_MA_CON_RATE, MAX_APPL_MA_CON_AMOUNT AS MAX_MA_CON_AMOUNT, "+
	        		"SEP_IRAS_INFO.REVENUE AS IRAS_REVENUE, SEP_IRAS_INFO.NTI AS IRAS_NTI, FILE_TYPE " + 
	        		"FROM STG_IBIS_D_R_SEP_MA_RATE STG " ;
	        
	        if (relYear.equals("1")) {
	        	sepSelectSql += "JOIN SEP_IRAS_INFO ON (STG.NRIC=SEP_IRAS_INFO.NRIC AND FILE_TYPE = 'F' AND SEP_IRAS_INFO.RELEVANT_YEAR = EXTRACT(YEAR FROM SYSDATE) - ?) " ;
	        } else if (relYear.equals("2")) {
	        	sepSelectSql += "JOIN SEP_IRAS_INFO ON (STG.NRIC=SEP_IRAS_INFO.NRIC AND FILE_TYPE = 'D' AND SEP_IRAS_INFO.RELEVANT_YEAR = EXTRACT(YEAR FROM SYSDATE) - ?) " ;
	        }        
	        logger.info("STG_IBIS_D_R_SEP_MA_RATE select query : " + sepSelectSql);
	        sepSelectSql += "LEFT JOIN SEP_INFO on (SEP_IRAS_INFO.NRIC=SEP_INFO.NRIC) " + 
				    		"WHERE 1=1 "  +
				    		"AND STG.PROCESS_FLG='N' ";
	        
	        
	       Integer count = jdbcTemplate.query(
	        		sepSelectSql,
	    			new Object[]{relYear},
	                new IBIS_D_R_SEP_MA_Rate_Extractor_Service(semyrSepMARateService, relYear, dataSource));
	       
	       logger.info("Total caye con updated records : " + count);
	       
	       
	     
	        
	        return RepeatStatus.FINISHED;
	    }   
	    
	    
	    
	    
	    
	    
	}