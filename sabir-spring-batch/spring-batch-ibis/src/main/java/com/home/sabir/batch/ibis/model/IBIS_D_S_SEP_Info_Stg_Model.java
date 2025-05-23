package com.home.sabir.batch.ibis.model;

import java.sql.Date;

public class IBIS_D_S_SEP_Info_Stg_Model {

	private String RECORD_TYPE; 
	private String SEP_NRIC; 
	private Double CAYE_CON_RATE;
	private Date CREATE_TS;
	
	public String getRECORD_TYPE() {
		return RECORD_TYPE;
	}
	public void setRECORD_TYPE(String rECORD_TYPE) {
		RECORD_TYPE = rECORD_TYPE;
	}
	public String getSEP_NRIC() {
		return SEP_NRIC;
	}
	public void setSEP_NRIC(String sEP_NRIC) {
		SEP_NRIC = sEP_NRIC;
	}
	public Double getCAYE_CON_RATE() {
		return CAYE_CON_RATE;
	}
	public void setCAYE_CON_RATE(Double cAYE_CON_RATE) {
		CAYE_CON_RATE = cAYE_CON_RATE;
	}
	public Date getCREATE_TS() {
		return CREATE_TS;
	}
	public void setCREATE_TS(Date cREATE_TS) {
		CREATE_TS = cREATE_TS;
	}
	@Override
	public String toString() {
		return "SEPFDDRStgModel [RECORD_TYPE=" + RECORD_TYPE + ", SEP_NRIC=" + SEP_NRIC + ", CAYE_CON_RATE="
				+ CAYE_CON_RATE + ", CREATE_TS=" + CREATE_TS + "]";
	}
	
	
	
}
