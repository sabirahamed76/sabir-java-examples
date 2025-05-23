package com.home.sabir.batch.ibis.config.mapper.row;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import com.home.sabir.batch.ibis.model.IBIS_D_R_SEP_MA_Rate_Mdl;

public class IBIS_D_R_SEP_MA_Rate_Row implements RowMapper<IBIS_D_R_SEP_MA_Rate_Mdl> {
	
	private static final Logger logger = LogManager.getLogger(IBIS_D_R_SEP_MA_Rate_Row.class);
	
	@Override
	public IBIS_D_R_SEP_MA_Rate_Mdl mapRow(ResultSet rs, int rowNum) throws SQLException {
		IBIS_D_R_SEP_MA_Rate_Mdl mdl = new IBIS_D_R_SEP_MA_Rate_Mdl();
		
	
		
		mdl.setSEP_IRAS_TXN_ID(rs.getLong("SEP_IRAS_TXN_ID"));
		mdl.setSEP_IRAS_INFO_ID(rs.getLong("SEP_IRAS_INFO_ID"));
		mdl.setMA_CON_RATE(rs.getDouble("MA_CON_RATE"));
		mdl.setMAX_MA_CON_RATE(rs.getDouble("MAX_MA_CON_RATE"));
		mdl.setMAX_MA_CON_AMOUNT(rs.getDouble("MAX_MA_CON_AMOUNT"));
		mdl.setIRAS_REVENUE(rs.getDouble("IRAS_REVENUE"));
		mdl.setIRAS_NTI(rs.getDouble("IRAS_NTI"));
		mdl.setMA_CON_AMOUNT(rs.getDouble("MA_CON_AMOUNT"));
		mdl.setNRIC(rs.getString("NRIC"));

		return mdl;
	}

}