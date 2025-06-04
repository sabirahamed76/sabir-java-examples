package com.home.sabir.batch.admin.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ADMIN_D_P_SEP_Caye_Rate_Info_Service {

	private static final Logger logger = LogManager.getLogger(ADMIN_D_P_SEP_Caye_Rate_Info_Service.class);

	@Autowired
	private DataSource dataSource;
	
	private NamedParameterJdbcTemplate jdbcTemplate;

	@PostConstruct
	public void setJdbcTemplate() {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	private static final String sepCayeRateInfoInsertSql = "INSERT INTO SEP_CAYE_RATE_INFO(SEP_CAYE_RATE_INFO_ID,CAYE_APPL_YEAR,CAYE_CON_RATE,CREATED_SOURCE,CREATED_BY,CREATED_TS,UPDATED_SOURCE,UPDATED_BY,UPDATED_TS,NRIC "
			+ ",INPUT_SOURCE) VALUES(SEP_CAYE_RATE_INFO_SEQ.NEXTVAL,TO_CHAR(SYSDATE, 'YYYY'),:CAYE_CON_RATE,'BATCH', (SELECT BATCH_JOB_ID FROM BATCH_JOB WHERE BATCH_JOB_NAME='ADMIN_SEP_D_P_Caye_Rate_Update_Job'),CURRENT_TIMESTAMP,'','','',:NRIC, :INPUT_SOURCE)";

	private static final String sepCayeRateInfoUpdSql = "UPDATE SEP_CAYE_RATE_INFO SET CAYE_CON_RATE = :CAYE_CON_RATE, INPUT_SOURCE = :INPUT_SOURCE, UPDATED_SOURCE = 'BATCH', UPDATED_BY = (SELECT BATCH_JOB_ID FROM BATCH_JOB WHERE BATCH_JOB_NAME='ADMIN_SEP_D_P_Caye_Rate_Update_Job'), UPDATED_TS = CURRENT_TIMESTAMP WHERE SEP_CAYE_RATE_INFO_ID = :SEP_CAYE_RATE_INFO_ID ";	

	private static final String sepInfoUpdSql = "UPDATE SEP_INFO SET CAYE_RATE_UPD_FLAG='Y' ,UPDATED_SOURCE = 'BATCH', UPDATED_BY = (SELECT BATCH_JOB_ID FROM BATCH_JOB WHERE BATCH_JOB_NAME='ADMIN_SEP_D_P_Caye_Rate_Update_Job'), UPDATED_TS = CURRENT_TIMESTAMP WHERE  NRIC = :NRIC  ";

	@Transactional
	public void updateRecords(List<SqlParameterSource> sepCayeRateInfoUpdList,
			List<SqlParameterSource> sepCayeRateInfoInsList) {		
		logger.info("SEP CAYE Rate INFO Upd List size :" + sepCayeRateInfoUpdList.size());
		logger.info("SEP CAYE Rate INFO Ins List size :" + sepCayeRateInfoInsList.size());
		
		logger.info("SEP INFO update query - " + sepInfoUpdSql);
		int[] updateCount = jdbcTemplate.batchUpdate(sepInfoUpdSql,
				sepCayeRateInfoUpdList.toArray(new SqlParameterSource[0]));
		
		logger.info("Update SEP INFO Update count list size :" + updateCount.length);
		
		updateCount = jdbcTemplate.batchUpdate(sepInfoUpdSql,
				sepCayeRateInfoInsList.toArray(new SqlParameterSource[0]));
		
		logger.info("Update SEP INFO Update count list size :" + updateCount.length);
		
		if (sepCayeRateInfoInsList != null && !sepCayeRateInfoInsList.isEmpty()) {
			logger.info("SEP CAYE Rate INFO insert query - " + sepCayeRateInfoInsertSql);
			int[] insertCount = jdbcTemplate.batchUpdate(sepCayeRateInfoInsertSql,
					sepCayeRateInfoInsList.toArray(new SqlParameterSource[0]));
			logger.info("Insert SEP CAYE Rate INFO count list size :" + insertCount.length);			
		} else {
			logger.info("SEP CAYE Rate INFO update query - " + sepCayeRateInfoUpdSql);
			updateCount = jdbcTemplate.batchUpdate(sepCayeRateInfoUpdSql,
					sepCayeRateInfoUpdList.toArray(new SqlParameterSource[0]));
			logger.info("Update SEP CAYE Rate INFO count list size :" + updateCount.length);
		}
	}

	@Transactional
	public void updatecayeTxn(String transDate) {
		logger.info("SEP_CAYE_RATE_UPD_TXN to be updated for transDate - " + transDate);
		if(null != transDate && !transDate.trim().equals("")) {			
			String sepCayeRateUpdTxnUpdSql = "UPDATE SEP_CAYE_RATE_UPD_TXN SET TRANS_STATUS='APP',UPDATED_SOURCE = 'BATCH', " +
					"UPDATED_BY = (SELECT BATCH_JOB_ID FROM BATCH_JOB WHERE BATCH_JOB_NAME='ADMIN_SEP_D_P_Caye_Rate_Update_Job'), UPDATED_TS = CURRENT_TIMESTAMP " +
					"WHERE  TRANS_STATUS='SUB' AND TO_DATE(trunc(trans_date),'DD-MON-YY') <= TO_DATE('" + transDate + "', 'DD-MON-YY') ";
			logger.info("sepCayeRateUpdTxnUpdSql - " + sepCayeRateUpdTxnUpdSql);

			int rows = new JdbcTemplate(dataSource).update(sepCayeRateUpdTxnUpdSql);
			logger.info("Num of records updated in SEP_CAYE_RATE_UPD_TXN : "+rows);
		}
	}
	
	
	@Transactional
	public void updateAdminCayeTxn(String transDate) {
		logger.info("SEP_CAYE_RATE_UPD_ADM_TXN to be updated for transDate - " + transDate);
		if(null != transDate && !transDate.trim().equals("")) {			
			String sepCayeRateUpdTxnUpdSql = "UPDATE SEP_CAYE_RATE_UPD_ADM_TXN SET TRANS_STATUS='APP'" +
//					+ ", UPDATED_SOURCE = 'BATCH', " +
//					"UPDATED_BY = (SELECT BATCH_JOB_ID FROM BATCH_JOB WHERE BATCH_JOB_NAME='ADMIN_SEP_D_P_Caye_Rate_Update_Job'), UPDATED_TS = CURRENT_TIMESTAMP " +
					"WHERE  TRANS_STATUS='SUB' AND TO_DATE(trunc(trans_date),'DD-MON-YY') <= TO_DATE('" + transDate + "', 'DD-MON-YY') ";
			logger.info("sepCayeRateUpdTxnUpdSql - " + sepCayeRateUpdTxnUpdSql);

			int rows = new JdbcTemplate(dataSource).update(sepCayeRateUpdTxnUpdSql);
			logger.info("Num of records updated in SEP_CAYE_RATE_UPD_ADM_TXN : "+rows);
		}
	}
	
	
	/*@Transactional
	public void updateAdminTxnRecords(List<SqlParameterSource> sepCayeRateInfoList) {
		logger.info("SEP CAYE Rate INFO Upd List size :" + sepCayeRateInfoList.size());
		logger.info("SEP CAYE Rate INFO update query - " + sepCayeRateInfoUpdSql);
		int[] updateCount = jdbcTemplate.batchUpdate(sepCayeRateInfoUpdSql,
				sepCayeRateInfoList.toArray(new SqlParameterSource[0]));
		logger.info("Update SEP CAYE Rate INFO count list size :" + updateCount.length);

	}*/
}
