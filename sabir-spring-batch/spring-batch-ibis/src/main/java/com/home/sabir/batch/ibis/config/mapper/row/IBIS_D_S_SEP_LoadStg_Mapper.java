package com.home.sabir.batch.ibis.config.mapper.row;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import com.home.sabir.batch.core.util.StringUtils;
import com.home.sabir.batch.ibis.model.IBIS_D_S_SEP_Info_Model;

public class IBIS_D_S_SEP_LoadStg_Mapper implements RowMapper<IBIS_D_S_SEP_Info_Model>{
	
	private static final Logger logger = LogManager.getLogger(IBIS_D_S_SEP_LoadStg_Mapper.class);

	@Override
	public IBIS_D_S_SEP_Info_Model mapRow(ResultSet rs, int rowNum) throws SQLException {
			IBIS_D_S_SEP_Info_Model model = new IBIS_D_S_SEP_Info_Model();
			String nric =rs.getString("SEP_NRIC");
			String leftValue = "00";
			String rightValue = "00";
			model.setRECORD_TYPE(rs.getString("RECORD_TYPE"));
			model.setSEP_NRIC(nric);
			String cayeConRate=rs.getString("CAYE_CON_RATE");
			
			//cayeConRate=StringUtils.fillString("left", 4, cayeConRate, "0");
			
			if (cayeConRate!= null && cayeConRate.contains(".")) {
				leftValue = cayeConRate.substring(0,cayeConRate.indexOf('.'));
				rightValue = cayeConRate.substring(cayeConRate.indexOf('.')+1);
			}else if(cayeConRate!= null && Long.parseLong(cayeConRate)>0) {
				leftValue = StringUtils.fillString("left", 2, cayeConRate, "0");
			}
			
			
			
			leftValue = StringUtils.fillString("left", 2, leftValue, "0");
			rightValue = StringUtils.fillString("right", 2, rightValue, "0");
			
			
			model.setCAYE_CON_RATE(leftValue+rightValue);
			model.setFiller(StringUtils.fillString("left", 284, " ", " "));
			logger.info("Detail Row - " + model.toString());
			
		return model;
	}
}
