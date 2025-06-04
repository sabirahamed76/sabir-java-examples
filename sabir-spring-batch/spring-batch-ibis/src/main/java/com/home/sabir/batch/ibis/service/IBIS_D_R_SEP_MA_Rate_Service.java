/**
 * 
 */
package com.home.sabir.batch.ibis.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author SI20080821
 *
 */
@Service
public class IBIS_D_R_SEP_MA_Rate_Service {
	
	private static final Logger logger = LogManager.getLogger(IBIS_D_R_SEP_MA_Rate_Service.class);
	
	@Autowired
	private DataSource dataSource;
	
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	@PostConstruct
	public void setJdbcTemplate() {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	
	
	private static final String sepIRASTxnInsertSql =  "INSERT INTO SEP_IRAS_TXN (SEP_IRAS_TXN_ID, SEP_IRAS_INFO_ID, RELEVANT_YEAR, CAYE_APPL_YEAR, MA_CON_RATE, MAX_MA_CON_RATE, MAX_MA_CON_AMOUNT, CAYE_CON_RATE, FILE_TYPE, CREATED_SOURCE, CREATED_BY, CREATED_TS) "
			+ "VALUES (SEP_IRAS_TXN_SEQ.NEXTVAL, :SEP_IRAS_INFO_ID, :RELEVANT_YEAR, :APPL_YEAR,  :MA_CON_RATE, :MAX_MA_CON_RATE, :MAX_MA_CON_AMOUNT, :CAYE_CON_RATE, :FILE_TYPE, "
			+ "'BATCH', (SELECT BATCH_JOB_ID FROM BATCH_JOB WHERE BATCH_JOB_NAME = 'IBIS_D_R_SEP_MA_Rate_ETL_Job'), CURRENT_TIMESTAMP) ";
	
	private static final String sepCayeRateInfoInsertSql =  "INSERT INTO SEP_CAYE_RATE_INFO (SEP_CAYE_RATE_INFO_ID, NRIC, CAYE_APPL_YEAR, CAYE_CON_RATE, INPUT_SOURCE, CREATED_SOURCE, CREATED_BY, CREATED_TS) "
			+ "VALUES (SEP_CAYE_RATE_INFO_SEQ.NEXTVAL, :NRIC, :APPL_YEAR, :CAYE_CON_RATE, 'IRAS', 'BATCH', "
			+ "(SELECT BATCH_JOB_ID FROM BATCH_JOB WHERE BATCH_JOB_NAME = 'IBIS_D_R_SEP_MA_Rate_ETL_Job'), CURRENT_TIMESTAMP) ";
	
	private static final String sepCayeRateInfoUpdateSql = "UPDATE SEP_CAYE_RATE_INFO SET CAYE_CON_RATE = :CAYE_CON_RATE, INPUT_SOURCE = 'IRAS', UPDATED_SOURCE = 'BATCH', "
			+ "UPDATED_BY = (SELECT BATCH_JOB_ID FROM BATCH_JOB WHERE BATCH_JOB_NAME = 'IBIS_D_R_SEP_MA_Rate_ETL_Job'), UPDATED_TS = CURRENT_TIMESTAMP WHERE SEP_CAYE_RATE_INFO_ID = :SEP_CAYE_RATE_INFO_ID ";	
	
	private static final String stgUpdate = "UPDATE STG_IBIS_D_R_SEP_MA_RATE SET PROCESS_FLG='Y' WHERE NRIC = :NRIC";
	
	@Transactional
	public void updateRecords(String relYear, List<SqlParameterSource> commitList, List<SqlParameterSource> sepCayeRateInsList, List<SqlParameterSource> sepCayeRateUpdList) {
		int[] cnt;
		logger.info("*****IBIS_D_R_SEP_MA_Rate_Service.updateRecords() execution started*****");
		
		/*if (sepInfoList != null && !sepInfoList.isEmpty()) {
		jdbcTemplate.batchUpdate(sepInfoInsertSql, sepInfoList.toArray(new SqlParameterSource[0]));
	}*/
		
		if(!commitList.isEmpty()) {
			logger.info("sepIRASTxnInsertSql - " + sepIRASTxnInsertSql);
			cnt=jdbcTemplate.batchUpdate(sepIRASTxnInsertSql, commitList.toArray(new SqlParameterSource[0]));
			logger.info("number of records inserted in SEP_IRAS_TXN - " + cnt);
		}
		
		logger.info("relYear - " + relYear);
		if(relYear.equals("1")) {
			if (commitList != null && !commitList.isEmpty()) {
				logger.info("size commitList for relYear 1 - " + commitList.size());
				
				logger.info("sepCayeRateInfoInsertSql - " + sepCayeRateInfoInsertSql);
				cnt=jdbcTemplate.batchUpdate(sepCayeRateInfoInsertSql, commitList.toArray(new SqlParameterSource[0]));
				logger.info("number of records inserted in SEP_CAYE_RATE_INFO for relYear 1 - " + cnt);								
			}
		} else if(relYear.equals("2")) {
			logger.info("size sepCayeRateInsList for relYear 2 - " + sepCayeRateInsList.size());
			if(!sepCayeRateInsList.isEmpty()) {
				logger.info("sepCayeRateInfoInsertSql - " + sepCayeRateInfoInsertSql);
				cnt=jdbcTemplate.batchUpdate(sepCayeRateInfoInsertSql, sepCayeRateInsList.toArray(new SqlParameterSource[0]));
				logger.info("number of records inserted in SEP_CAYE_RATE_INFO for relYear 2 - " + cnt);
			}
			
			if(!sepCayeRateUpdList.isEmpty()) {
				logger.info("sepCayeRateInfoUpdateSql - " + sepCayeRateInfoUpdateSql);
				cnt=jdbcTemplate.batchUpdate(sepCayeRateInfoUpdateSql, sepCayeRateUpdList.toArray(new SqlParameterSource[0]));
				logger.info("number of records updated in SEP_CAYE_RATE_INFO for relYear 2 - " + cnt);
			}
		}
		
		logger.info("stgUpdate - " + stgUpdate);
		cnt=jdbcTemplate.batchUpdate(stgUpdate, commitList.toArray(new SqlParameterSource[0]));
		logger.info("number of records updated in STG_IBIS_D_R_SEP_MA_RATE for relYear 1 - " + cnt);

		logger.info("*****IBIS_D_R_SEP_MA_Rate_Service.updateRecords() execution ended*****");
	}
	
}
