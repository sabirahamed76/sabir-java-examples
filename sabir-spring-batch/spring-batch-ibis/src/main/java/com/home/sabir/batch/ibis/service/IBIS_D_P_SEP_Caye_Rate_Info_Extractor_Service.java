package com.home.sabir.batch.ibis.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.home.sabir.batch.ibis.model.IBIS_D_P_SEP_CAYE_RATE_INFO_Mdl;

public class IBIS_D_P_SEP_Caye_Rate_Info_Extractor_Service implements ResultSetExtractor<Long> {

	private static final Logger logger = LogManager.getLogger(IBIS_D_P_SEP_Caye_Rate_Info_Extractor_Service.class);

	private IBIS_D_P_SEP_Caye_Rate_Info_Service service;
	private DataSource dataSource;
	private String transDate;

	public IBIS_D_P_SEP_Caye_Rate_Info_Extractor_Service(IBIS_D_P_SEP_Caye_Rate_Info_Service service, DataSource dataSource, String transDate) {
		this.service = service;
		this.dataSource = dataSource;
		this.transDate = transDate;
	}

	@Override
	public Long extractData(ResultSet rs) throws SQLException, DataAccessException {
		Long count = 0L;
		Set<IBIS_D_P_SEP_CAYE_RATE_INFO_Mdl> sepCayeRateInfoInsMdlSet = new HashSet<>();
		Set<IBIS_D_P_SEP_CAYE_RATE_INFO_Mdl> sepCayeRateInfoUpdMdlSet = new HashSet<>();
		List<SqlParameterSource> sepCayeRateInfoUpdList = new ArrayList<>();
		List<SqlParameterSource> sepCayeRateInfoInsList = new ArrayList<>();

		IBIS_D_P_SEP_CAYE_RATE_INFO_Mdl sepCayeRateInfoInsMdl = new IBIS_D_P_SEP_CAYE_RATE_INFO_Mdl();
		IBIS_D_P_SEP_CAYE_RATE_INFO_Mdl sepCayeRateInfoUpdMdl = new IBIS_D_P_SEP_CAYE_RATE_INFO_Mdl();
		
		String sepCayeRateInfoSelectSql = "SELECT SEP_CAYE_RATE_INFO_ID FROM SEP_CAYE_RATE_INFO WHERE NRIC=:NRIC AND CAYE_APPL_YEAR=TO_CHAR(SYSDATE, 'YYYY')";

		NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		MapSqlParameterSource sqlParameters = new MapSqlParameterSource();

		while (rs.next()) {
			sepCayeRateInfoUpdMdl = new IBIS_D_P_SEP_CAYE_RATE_INFO_Mdl();
			String nric = "";
			
			sepCayeRateInfoUpdMdl.setTransDate(transDate);
			if (null != rs.getString("NRIC") && !rs.getString("NRIC").trim().equals("")) {
				nric = rs.getString("NRIC").trim();
				sepCayeRateInfoUpdMdl.setNRIC(nric);
			}
			if (null != rs.getString("CAYE_CON_RATE") && !rs.getString("CAYE_CON_RATE").trim().equals(""))
				sepCayeRateInfoUpdMdl.setCAYE_CON_RATE(rs.getDouble("CAYE_CON_RATE"));
			if (null != rs.getString("SEP_INFO_ID") && !rs.getString("SEP_INFO_ID").trim().equals(""))
				sepCayeRateInfoUpdMdl.setSEP_INFO_ID(rs.getLong("SEP_INFO_ID"));
			if (null != rs.getString("ADMIN_TXN_ID") && !rs.getString("ADMIN_TXN_ID").trim().equals(""))
				sepCayeRateInfoUpdMdl.setADMIN_TXN_ID(rs.getLong("ADMIN_TXN_ID"));

			sqlParameters.addValue("NRIC", nric);
			List<Long> sepCayeRateInfoIList = jdbcTemplate.queryForList(sepCayeRateInfoSelectSql, sqlParameters, Long.class);
			Long sepCayeRateInfoId = null;

			//logger.info("SEP NRIC : " + rs.getString("NRIC") + " :: CAYE_CON_RATE : " + rs.getDouble("CAYE_CON_RATE") + " :: sepCayeRateInfoId :" + sepCayeRateInfoId);

			if(CollectionUtils.isNotEmpty(sepCayeRateInfoIList))
				sepCayeRateInfoId = sepCayeRateInfoIList.get(0);
				
			if (sepCayeRateInfoId == null) {
				sepCayeRateInfoInsMdl = new IBIS_D_P_SEP_CAYE_RATE_INFO_Mdl();
				sepCayeRateInfoInsMdl.setNRIC(sepCayeRateInfoUpdMdl.getNRIC());
				sepCayeRateInfoInsMdl.setCAYE_CON_RATE(sepCayeRateInfoUpdMdl.getCAYE_CON_RATE());
				sepCayeRateInfoInsMdl.setSEP_INFO_ID(sepCayeRateInfoUpdMdl.getSEP_INFO_ID());
				sepCayeRateInfoInsMdl.setINPUT_SOURCE("SEP");
				sepCayeRateInfoInsMdlSet.add(sepCayeRateInfoInsMdl);
			} else {
				sepCayeRateInfoUpdMdl.setSEP_CAYE_RATE_INFO_ID(sepCayeRateInfoId);
				sepCayeRateInfoUpdMdl.setINPUT_SOURCE("SEP");
				/*if(null == sepCayeRateInfoUpdMdl.getADMIN_TXN_ID())
					sepCayeRateInfoUpdMdl.setINPUT_SOURCE("SEP");
				else sepCayeRateInfoUpdMdl.setINPUT_SOURCE("ADMIN");*/
				sepCayeRateInfoUpdMdlSet.add(sepCayeRateInfoUpdMdl);
			}

			
			if (!sepCayeRateInfoInsMdlSet.isEmpty()) {
				logger.info("Size of SEP caye Rate Info Insert List : " + sepCayeRateInfoInsMdlSet.size());

				for (IBIS_D_P_SEP_CAYE_RATE_INFO_Mdl model : sepCayeRateInfoInsMdlSet.stream().collect(Collectors.toList()))
					sepCayeRateInfoInsList.add(new BeanPropertySqlParameterSource(model));
			} else if (!sepCayeRateInfoUpdMdlSet.isEmpty()) {
				logger.info("Size of SEP caye Rate Info update List : " + sepCayeRateInfoUpdMdlSet.size());

				for (IBIS_D_P_SEP_CAYE_RATE_INFO_Mdl model : sepCayeRateInfoUpdMdlSet.stream().collect(Collectors.toList()))
					sepCayeRateInfoUpdList.add(new BeanPropertySqlParameterSource(model));
			}

			service.updateRecords(sepCayeRateInfoUpdList, sepCayeRateInfoInsList);
			sepCayeRateInfoInsMdlSet.clear();
			sepCayeRateInfoUpdMdlSet.clear();
			sepCayeRateInfoUpdList.clear();
			sepCayeRateInfoInsList.clear();
		}
		
		service.updatecayeTxn(this.transDate);
		return count;
	}
}
