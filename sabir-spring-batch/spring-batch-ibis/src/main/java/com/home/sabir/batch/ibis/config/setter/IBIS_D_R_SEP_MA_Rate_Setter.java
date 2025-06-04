package com.home.sabir.batch.ibis.config.setter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.batch.item.database.ItemPreparedStatementSetter;

import com.home.sabir.batch.ibis.model.IBIS_D_R_SEP_MA_Rate_Mdl;

public class IBIS_D_R_SEP_MA_Rate_Setter implements ItemPreparedStatementSetter<IBIS_D_R_SEP_MA_Rate_Mdl> {

	@Override
	public void setValues(IBIS_D_R_SEP_MA_Rate_Mdl item, PreparedStatement ps) throws SQLException {
		
		if(null != item.getRECORD_TYPE())
			ps.setString(1, item.getRECORD_TYPE());
		else ps.setNull(1, java.sql.Types.VARCHAR);
		if(null != item.getRECORD_TYPE())
			ps.setString(2, item.getNRIC());
		else ps.setNull(2, java.sql.Types.VARCHAR);
		if(null != item.getRECORD_TYPE())
			ps.setString(3, item.getAPPL_YEAR());
		else ps.setNull(3, java.sql.Types.VARCHAR);
		if(null != item.getRECORD_TYPE())
			ps.setString(4, item.getNTI());
		else ps.setNull(4, java.sql.Types.VARCHAR);
		if(null != item.getRECORD_TYPE())
			ps.setString(5, item.getAPPL_MA_CON_RATE());
		else ps.setNull(5, java.sql.Types.VARCHAR);
		if(null != item.getRECORD_TYPE())
			ps.setString(6, item.getMAX_APPL_MA_CON_RATE());
		else ps.setNull(6, java.sql.Types.VARCHAR);
		if(null != item.getRECORD_TYPE())
			ps.setString(7, item.getMAX_APPL_MA_CON_AMOUNT());
		else ps.setNull(7, java.sql.Types.VARCHAR);
	}

}
