package com.home.sabir.batch.admin.task;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.home.sabir.batch.admin.service.ADMIN_D_P_SEP_Caye_Rate_Info_Extractor_Service;
import com.home.sabir.batch.admin.service.ADMIN_D_P_SEP_Caye_Rate_Info_Service;
import com.home.sabir.batch.admin.service.ADMIN_D_P_SEP_Caye_Rate_Info_Upd_Admin_Txn_Extractor_Service;
import com.home.sabir.batch.core.util.DateUtils;

public class ADMIN_D_P_SEP_Caye_Rate_Update_Task implements Tasklet {

	private static final Logger logger = LogManager.getLogger(ADMIN_D_P_SEP_Caye_Rate_Update_Task.class);

	private DataSource dataSource;

	private ADMIN_D_P_SEP_Caye_Rate_Info_Service cayeRateInfoService;

	private String transDate;

	public ADMIN_D_P_SEP_Caye_Rate_Update_Task(DataSource dataSource, ADMIN_D_P_SEP_Caye_Rate_Info_Service cayeRateInfoService,
			String transDate) {
		this.dataSource = dataSource;
		this.cayeRateInfoService = cayeRateInfoService;
		this.transDate = transDate;
	}


	String selectCayeRateUpdateRetrieveSql = "SELECT DISTINCT txn.SEP_INFO_ID, " +
			" (SELECT CAYE_CON_RATE "+
			"  FROM SEP_CAYE_RATE_UPD_TXN T1 "+
			"  WHERE T1.SEP_CAYE_RATE_UPD_TXN_id = (SELECT max(SEP_CAYE_RATE_UPD_TXN_id)  "+
			"  FROM SEP_CAYE_RATE_UPD_TXN T2  "+
			"  WHERE 1=1  "+
			"  AND TRANS_STATUS='SUB'  "+
			"  AND trunc(trans_date) <= TO_DATE(:transDate, 'DD-MON-YY')  "+
			"  AND CAYE_APPL_YEAR=TO_CHAR(SYSDATE, 'YYYY')  "+
			"  AND SEP_INFO_ID = txn.SEP_INFO_ID)) as CAYE_CON_RATE, "+
			"  (SELECT NRIC FROM SEP_INFO WHERE SEP_INFO_ID=txn.SEP_INFO_ID) as NRIC,  "+
			"	 ADMIN_TXN_ID " +
			"  FROM SEP_CAYE_RATE_UPD_TXN txn  "+
			"  WHERE 1=1  "+
			"  AND TRANS_STATUS='SUB'  "+
			"  AND trunc(trans_date) <= TO_DATE(:transDate, 'DD-MON-YY')  "+
			"  AND CAYE_APPL_YEAR=TO_CHAR(SYSDATE, 'YYYY') ";
	
	
	String selectCayeRateUpdateAdminRetrieveSql = "SELECT DISTINCT txn.SEP_INFO_ID, " +
			" (SELECT CAYE_CON_RATE "+
			"  FROM SEP_CAYE_RATE_UPD_ADM_TXN T1 "+
			"  WHERE T1.SEP_CAYE_RATE_UPD_ADM_TXN_id = (SELECT max(SEP_CAYE_RATE_UPD_ADM_TXN_id)  "+
			"  FROM SEP_CAYE_RATE_UPD_ADM_TXN T2  "+
			"  WHERE 1=1  "+
			"  AND TRANS_STATUS='SUB'  "+
			"  AND trunc(trans_date) <= TO_DATE(:transDate, 'DD-MON-YY')  "+
			"  AND SEP_INFO_ID = txn.SEP_INFO_ID)) as CAYE_CON_RATE, "+
			"  (SELECT NRIC FROM SEP_INFO WHERE SEP_INFO_ID=txn.SEP_INFO_ID) as NRIC,  "+
			"	 ADMIN_TXN_ID " +
			"  FROM SEP_CAYE_RATE_UPD_ADM_TXN txn  "+
			"  WHERE 1=1  "+
			"  AND TRANS_STATUS='SUB'  "+
			"  AND trunc(trans_date) <= TO_DATE(:transDate, 'DD-MON-YY')  ";



	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		MapSqlParameterSource sqlParameters = new MapSqlParameterSource();

		if (transDate != null && !transDate.equals("")) {
			logger.info("SEP Caye Rate Update Job parameter TransDate : " + transDate);
			boolean isDateValid = DateUtils.isDateValid(transDate, DateUtils.DATE_FORMAT_DD_MMM_YY);
			if (!isDateValid) {
				throw new Exception("Transaction date " + transDate + " is not in valid date format "
						+ DateUtils.DATE_FORMAT_DD_MMM_YY);
			}
		} else {
			logger.info("SEP Caye Rate Update Job processing with sysdate -1 ...");
			Calendar cal = Calendar.getInstance();
			// subtracting a day
			cal.add(Calendar.DATE, -1);

			SimpleDateFormat s = new SimpleDateFormat(DateUtils.DATE_FORMAT_DD_MMM_YY);
			transDate = s.format(new Date(cal.getTimeInMillis()));
		}
		logger.info("SEP Caye Rate JOB Trans Date : " + transDate);
		sqlParameters.addValue("transDate", transDate);


		logger.info("SEP Caye Rate Select query - " + selectCayeRateUpdateRetrieveSql);
		Long totalRecCount = jdbcTemplate.query(selectCayeRateUpdateRetrieveSql, sqlParameters, 
				new ADMIN_D_P_SEP_Caye_Rate_Info_Extractor_Service(cayeRateInfoService, dataSource,transDate));
		logger.info("Total SEP_CAYE_RATE_INFO records proccessed : " + totalRecCount);
		
		logger.info("SEP Caye Rate Admin Select query - " + selectCayeRateUpdateAdminRetrieveSql);
		totalRecCount = jdbcTemplate.query(selectCayeRateUpdateAdminRetrieveSql, sqlParameters, 
				new ADMIN_D_P_SEP_Caye_Rate_Info_Upd_Admin_Txn_Extractor_Service(cayeRateInfoService, dataSource, transDate));
		logger.info("Total SEP_CAYE_RATE_INFO Admin records proccessed : " + totalRecCount);

		return RepeatStatus.FINISHED;
	}

}
