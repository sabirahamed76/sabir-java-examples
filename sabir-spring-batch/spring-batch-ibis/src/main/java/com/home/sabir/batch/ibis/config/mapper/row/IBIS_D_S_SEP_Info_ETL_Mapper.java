package com.home.sabir.batch.ibis.config.mapper.row;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.home.sabir.batch.ibis.model.IBIS_D_S_SEP_Info_Stg_Model;

public class IBIS_D_S_SEP_Info_ETL_Mapper implements RowMapper<IBIS_D_S_SEP_Info_Stg_Model>{

	@Override
	public IBIS_D_S_SEP_Info_Stg_Model mapRow(ResultSet rs, int rowNum) throws SQLException {
		IBIS_D_S_SEP_Info_Stg_Model model = new IBIS_D_S_SEP_Info_Stg_Model();
		
		model.setRECORD_TYPE("002");
		model.setSEP_NRIC(rs.getString("NRIC"));
		model.setCAYE_CON_RATE(rs.getDouble("CAYE_CON_RATE"));
		model.setCREATE_TS(new Date(new java.util.Date().getTime())); //CURRENT_TIMESTAMP can be used directly

		return model;
	}

}
