/**
 * 
 */
package com.home.sabir.batch.ibis.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.home.sabir.batch.core.constants.BatchJobConstants;
import com.home.sabir.batch.core.util.NumberUtils;
import com.home.sabir.batch.ibis.model.IBIS_D_R_SEP_MA_Rate_Mdl;

/**
 * @author SI20080821
 *
 */
public class IBIS_D_R_SEP_MA_Rate_Extractor_Service implements ResultSetExtractor<Integer> {

	private static final Logger logger = LogManager.getLogger(IBIS_D_R_SEP_MA_Rate_Extractor_Service.class);

	private IBIS_D_R_SEP_MA_Rate_Service semyrSepMARateService;

	private String relYear;

	private DataSource dataSource;

	public IBIS_D_R_SEP_MA_Rate_Extractor_Service(IBIS_D_R_SEP_MA_Rate_Service semyrSepMARateService, String relYear, DataSource dataSource) {
		this.semyrSepMARateService = semyrSepMARateService;
		this.relYear = relYear;
		this.dataSource = dataSource;
	}

	@Override
	public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
		int count = 0;
		int commitCount = 1;
		List<SqlParameterSource> commitList = new ArrayList<>();
//		List<SqlParameterSource> sepInfoList = new ArrayList<>();
		List<SqlParameterSource> sepCayeRateInsList = new ArrayList<>();
		List<SqlParameterSource> sepCayeRateUpdList = new ArrayList<>();
		List<Integer> sepCayeRateInfoIDList = new ArrayList<>();
		while (rs.next()) {			
			logger.info("Commit count : "+ commitCount);
			IBIS_D_R_SEP_MA_Rate_Mdl mdl = new IBIS_D_R_SEP_MA_Rate_Mdl();

			mdl.setSEP_IRAS_INFO_ID(rs.getLong("SEP_IRAS_INFO_ID"));
			mdl.setSEP_INFO_ID(rs.getLong("SEP_INFO_ID"));
			mdl.setNRIC(rs.getString("NRIC"));
			mdl.setRELEVANT_YEAR(rs.getInt("RELEVANT_YEAR"));
			mdl.setAPPL_YEAR(rs.getString("APPL_YEAR"));
			mdl.setIRAS_REVENUE(rs.getDouble("IRAS_REVENUE"));
			mdl.setIRAS_NTI(rs.getDouble("IRAS_NTI"));
			mdl.setFILE_TYPE(rs.getString("FILE_TYPE"));

			BigDecimal zeroValue = new BigDecimal(0.0);
			BigDecimal hundredValue = new BigDecimal(100.0);
			BigDecimal cayeConRate=zeroValue;
			BigDecimal maConAmount=zeroValue;
			BigDecimal irasRevenue=rs.getBigDecimal("IRAS_REVENUE"); //new BigDecimal(mdl.getIRAS_REVENUE());
			BigDecimal irasNti= rs.getBigDecimal("IRAS_NTI"); //new IRAS_REVENUEBigDecimal(mdl.getIRAS_NTI());			
			BigDecimal maConRate = rs.getBigDecimal("MA_CON_RATE").divide(BigDecimal.valueOf(10000));
			BigDecimal maxMaConRate = rs.getBigDecimal("MAX_MA_CON_RATE").divide(BigDecimal.valueOf(10000));
			BigDecimal maxMaConAmount = rs.getBigDecimal("MAX_MA_CON_AMOUNT").divide(BigDecimal.valueOf(100));

			maConAmount=irasNti.multiply(maConRate);	
			maConAmount=maConAmount.divide(hundredValue);			
			maConAmount=new BigDecimal(maConAmount.toBigInteger());


			if (maConAmount.compareTo(maxMaConAmount)==1)
				maConAmount=maxMaConAmount;
			if (irasRevenue.compareTo(zeroValue)==1) {
				cayeConRate = maConAmount.divide(irasRevenue, 10, RoundingMode.HALF_DOWN);
				cayeConRate = cayeConRate.multiply(hundredValue);				
			}	else {
				cayeConRate=maConAmount.divide(irasNti, 10, RoundingMode.HALF_DOWN);
				cayeConRate=cayeConRate.multiply(hundredValue);
			}
			if (cayeConRate.compareTo(maxMaConRate)==1)
				cayeConRate=maxMaConRate;


			Double d = cayeConRate.doubleValue();
			BigDecimal cayeConRateTrunc = NumberUtils.truncateDecimal(d,2);
			d = cayeConRateTrunc.doubleValue();
			mdl.setCAYE_CON_RATE(d);

			mdl.setMA_CON_RATE(maConRate.doubleValue());
			mdl.setMAX_MA_CON_RATE(maxMaConRate.doubleValue());
			mdl.setMAX_MA_CON_AMOUNT(maxMaConAmount.doubleValue());

			logger.info("value of relYear - "  + relYear);			

			commitList.add(new BeanPropertySqlParameterSource(mdl));

			/*if(sepInfoId == null || sepInfoId==0) {
			sepInfoList.add(new BeanPropertySqlParameterSource(mdl));
			}*/

			if("2".equals(relYear)) {
				logger.info("relYear is 2");
				JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
				String sepCayeRateInfoExistsSql = "SELECT DISTINCT SEP_CAYE_RATE_INFO_ID FROM SEP_CAYE_RATE_INFO WHERE NRIC = '" + mdl.getNRIC().trim() + "' AND TRUNC(CAYE_APPL_YEAR) = EXTRACT(YEAR FROM SYSDATE) FETCH FIRST 1 ROWS ONLY ";
				String sepCayeRateInfoExistsForSEPorADMINSql = "SELECT DISTINCT 'Y' FROM SEP_CAYE_RATE_INFO WHERE INPUT_SOURCE IN ('SEP', 'ADMIN') AND SEP_CAYE_RATE_INFO_ID = ";

				logger.info("sepCayeRateInfoExistsSql - " + sepCayeRateInfoExistsSql);
				sepCayeRateInfoIDList = jdbcTemplate.queryForList(sepCayeRateInfoExistsSql, Integer.class);
				/*if(CollectionUtils.isNotEmpty(sepCayeRateInfoIDList)) {
					logger.info("sepCayeRateInfoIDList is not empty");
					for(Integer sepCayeRateInfoID : sepCayeRateInfoIDList) {
						logger.info("sepCayeRateInfoID retrieved for current CAYE_APPL_YEAR and NRIC (" + mdl.getNRIC() + ") is " + sepCayeRateInfoID);
						logger.info("sepCayeRateInfoExistsForSEPorADMINSql - " + sepCayeRateInfoExistsForSEPorADMINSql + sepCayeRateInfoID);
						exists = jdbcTemplate.queryForList(sepCayeRateInfoExistsForSEPorADMINSql + sepCayeRateInfoID, String.class);
						if(CollectionUtils.isNotEmpty(exists) && exists.get(0).equals("Y")) {
							logger.info("SEP_CAYE_RATE_INFO_ID has records for NRIC (" + mdl.getNRIC() + ") which were updated by SEP/ADMIN. So these can be ignored.");							
							continue;
						}
						else {
							logger.info("SEP_CAYE_RATE_INFO_ID does not have any records for NRIC (" + mdl.getNRIC() + ") which were updated by SEP/ADMIN. So these have to be updated with CAYE_CON_RATE - " + mdl.getCAYE_CON_RATE());
							mdl.setSEP_CAYE_RATE_INFO_ID(sepCayeRateInfoID);
							sepCayeRateUpdList.add(new BeanPropertySqlParameterSource(mdl));
						}
					}
				} else */
				if(CollectionUtils.isEmpty(sepCayeRateInfoIDList)) 
					sepCayeRateInsList.add(new BeanPropertySqlParameterSource(mdl));
			}

			logger.info("value of COMMIT_COUNT constant- " + BatchJobConstants.COMMIT_COUNT);
			if(commitCount == BatchJobConstants.COMMIT_COUNT) {
				logger.info("Size of commitCount - " + commitCount);

				logger.info("Updating the values");
				semyrSepMARateService.updateRecords(relYear, commitList, sepCayeRateInsList, sepCayeRateUpdList);

				commitCount = 1;
				logger.info("Total count :" + count);

				logger.info("Resetting the update list");				
				commitList = new ArrayList<>();
				sepCayeRateInsList = new ArrayList<>();
				sepCayeRateUpdList = new ArrayList<>();
				//sepInfoList = new ArrayList<>();
			}else {
				commitCount++;
			}

			count++;

		}

		if(!commitList.isEmpty() || !sepCayeRateInsList.isEmpty() || !sepCayeRateUpdList.isEmpty()) {
			logger.info("updating records outside...");			
			semyrSepMARateService.updateRecords(relYear, commitList, sepCayeRateInsList, sepCayeRateUpdList);
		}

		return count;
	}







}
