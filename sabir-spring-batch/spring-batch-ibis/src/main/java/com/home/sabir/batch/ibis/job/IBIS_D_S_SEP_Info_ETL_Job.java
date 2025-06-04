package com.home.sabir.batch.ibis.job;

import java.time.DayOfWeek;
import java.time.LocalDate;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import com.home.sabir.batch.core.model.BatchParams;
import com.home.sabir.batch.core.task.TableOpsTasklet;

@Configuration
@Lazy
public class IBIS_D_S_SEP_Info_ETL_Job {
	
	private static final Logger logger = LogManager.getLogger(IBIS_D_S_SEP_Info_ETL_Job.class);
	
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private BatchParams batchParams;
	

	private String sepFDStgTruncate = "CALL PRC_TRUNCATE_TABLE('IBIS_D_S_SEP_CAYE_RATE')";



	//Load SEP_INFO and SEP_CAYE_RATE_INFO into Staging table
	@Bean
	public Job IBIS_D_S_SEP_Info_ETL() {
		logger.info("IBIS_D_S_SEP_Info_ETL started...");
		String forceRun = (String) batchParams.get("forceRun");
		LocalDate date = LocalDate.now();
		//if(date.getDayOfWeek().equals(DayOfWeek.MONDAY) || (forceRun!=null && forceRun.equals("Y"))) {
			return jobBuilderFactory.get("IBIS_D_S_SEP_Info_ETL")
					.incrementer(new RunIdIncrementer())
					.start(BANKWSSEPInfoStgTruncateStep()) //Truncate Stg table - comment if not required
					.next(BANKWSSEPInfoETLLoadStep()) // READ Txn Tables and Load into DR Staging Table
					.next(BANKWSSEPInfoUpd())
					.build();
		/*}else {
			logger.info("Today is not Monday.. So job cannot be run");
			return jobBuilderFactory.get("IBIS_D_S_SEP_Info_ETL")
					.incrementer(new RunIdIncrementer())
					.start(BANKWSSEPInfoStgExitStep())
					.build();
		}*/
	}
	
	@Bean
    public Step BANKWSSEPInfoStgTruncateStep(){
        return stepBuilderFactory.get("BANKWSSEPInfoStgTruncateStep")
        		.tasklet(tasklet(sepFDStgTruncate))
                .build();  
        
    }
	
	@Bean
    public Step BANKWSSEPInfoStgExitStep(){
		return stepBuilderFactory.get("BANKWSSEPInfoStgExitStep")
				.tasklet(new Tasklet() {					
					@Override
					public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
						return RepeatStatus.FINISHED;
					}
				})
				.build();
	}
	
	
	
	private Tasklet tasklet(String sql) {
        return new TableOpsTasklet(sql, dataSource);
    }
	
	
	@Bean
	public Step BANKWSSEPInfoETLLoadStep() {	
			
		String applYear = (String) batchParams.get("applYear");
		String appyear = "";
		if (null!=applYear && !applYear.trim().equals("")){
			//appyear = "'" + applYear.trim() + "'";
			appyear = "extract(year from sysdate)+"+applYear.trim();
		}else{
			appyear = "extract(year from sysdate)";
		}
		String sepStgLoad_SEP_CAYE_Select_Full_sql = "";
		
	   /*
        sepStgLoad_SEP_CAYE_Select_Full_sql = 
                    "INSERT INTO STG_IBIS_D_S_SEP_CAYE_RATE (RECORD_TYPE,SEP_NRIC,CAYE_CON_RATE,CREATE_TS) "+
                    "SELECT '002',nric,caye_con_rate,current_timestamp " +
                    "FROM ( "+
                                      "SELECT si.nric,caye_con_rate "+
                                      "FROM sep_info si "+
                                      "LEFT JOIN sep_caye_rate_info scri ON (scri.nric=si.nric and CAYE_APPL_YEAR=year(curdate())) "+
                                      "WHERE 1=1 AND EXCEPTION_TAG = 'N') x " +
                    "WHERE x.CAYE_CON_RATE!=0 "+
                    "AND x.CAYE_CON_RATE IS NOT NULL";*/

        sepStgLoad_SEP_CAYE_Select_Full_sql = 
                    "INSERT INTO STG_IBIS_D_S_SEP_CAYE_RATE (RECORD_TYPE,SEP_NRIC,CAYE_CON_RATE,CREATE_TS) "+
                    "SELECT '002',nric,caye_con_rate,current_timestamp " +
                    "FROM ( "+
                                      "SELECT si.nric,caye_con_rate "+
                                      "FROM sep_info si "+
                                      "LEFT JOIN sep_caye_rate_info scri ON (scri.nric=si.nric and CAYE_APPL_YEAR=year(curdate())) "+
                                      ") x ";
		
		
		return stepBuilderFactory.get("IRAS_Y_R_SEP_REV_NTI_ETLStep")
				.tasklet(tasklet(sepStgLoad_SEP_CAYE_Select_Full_sql))
					.build();
	}	

	@Bean
    public Step BANKWSSEPInfoUpd(){
		String sepInfoUpdSql = "UPDATE SEP_INFO SET CAYE_RATE_UPD_FLAG = 'N', UPDATED_SOURCE = 'BATCH', UPDATED_BY = " +
				"(SELECT BATCH_JOB_ID FROM BATCH_JOB WHERE BATCH_JOB_NAME='IBIS_D_S_SEP_Info_ETL'), UPDATED_TS = CURRENT_TIMESTAMP " ;
        return stepBuilderFactory.get("BANKWSSEPInfoUpd")
        		.tasklet(tasklet(sepInfoUpdSql))
                .build();
    }
}
