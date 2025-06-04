package com.home.sabir.batch.ibis.model;

public class IBIS_D_P_SEP_CAYE_RATE_INFO_Mdl {

	private Long SEP_CAYE_RATE_INFO_ID;
	private Long SEP_CAYE_RATE_UPD_TXN_ID;
	private Long CAYE_APPL_YEAR;
	private Double CAYE_CON_RATE;
	private String CREATED_SOURCE;
	private String CREATED_BY;
	private String CREATED_TS;
	private String UPDATED_SOURCE;
	private String UPDATED_BY;
	private String UPDATED_TS;
	private String NRIC;
	private String INPUT_SOURCE;
	private Long SEP_INFO_ID;
	private String transDate;
	private Long ADMIN_TXN_ID;

	public String getTransDate() {
		return transDate;
	}

	public void setTransDate(String transDate) {
		this.transDate = transDate;
	}

	public Long getSEP_CAYE_RATE_UPD_TXN_ID() {
		return SEP_CAYE_RATE_UPD_TXN_ID;
	}

	public void setSEP_CAYE_RATE_UPD_TXN_ID(Long sEP_CAYE_RATE_UPD_TXN_ID) {
		SEP_CAYE_RATE_UPD_TXN_ID = sEP_CAYE_RATE_UPD_TXN_ID;
	}
	
	public Long getSEP_CAYE_RATE_INFO_ID() {
		return SEP_CAYE_RATE_INFO_ID;
	}

	public Long getCAYE_APPL_YEAR() {
		return CAYE_APPL_YEAR;
	}

	public Double getCAYE_CON_RATE() {
		return CAYE_CON_RATE;
	}

	public String getCREATED_SOURCE() {
		return CREATED_SOURCE;
	}

	public String getCREATED_BY() {
		return CREATED_BY;
	}

	public String getCREATED_TS() {
		return CREATED_TS;
	}

	public String getUPDATED_SOURCE() {
		return UPDATED_SOURCE;
	}

	public String getUPDATED_BY() {
		return UPDATED_BY;
	}

	public String getUPDATED_TS() {
		return UPDATED_TS;
	}

	public String getNRIC() {
		return NRIC;
	}

	public String getINPUT_SOURCE() {
		return INPUT_SOURCE;
	}

	public void setSEP_CAYE_RATE_INFO_ID(Long sEP_CAYE_RATE_INFO_ID) {
		SEP_CAYE_RATE_INFO_ID = sEP_CAYE_RATE_INFO_ID;
	}

	public void setCAYE_APPL_YEAR(Long cAYE_APPL_YEAR) {
		CAYE_APPL_YEAR = cAYE_APPL_YEAR;
	}

	public void setCAYE_CON_RATE(Double cAYE_CON_RATE) {
		CAYE_CON_RATE = cAYE_CON_RATE;
	}

	public void setCREATED_SOURCE(String cREATED_SOURCE) {
		CREATED_SOURCE = cREATED_SOURCE;
	}

	public void setCREATED_BY(String cREATED_BY) {
		CREATED_BY = cREATED_BY;
	}

	public void setCREATED_TS(String cREATED_TS) {
		CREATED_TS = cREATED_TS;
	}

	public void setUPDATED_SOURCE(String uPDATED_SOURCE) {
		UPDATED_SOURCE = uPDATED_SOURCE;
	}

	public void setUPDATED_BY(String uPDATED_BY) {
		UPDATED_BY = uPDATED_BY;
	}

	public void setUPDATED_TS(String uPDATED_TS) {
		UPDATED_TS = uPDATED_TS;
	}

	public void setNRIC(String nRIC) {
		NRIC = nRIC;
	}

	public void setINPUT_SOURCE(String iNPUT_SOURCE) {
		INPUT_SOURCE = iNPUT_SOURCE;
	}

	public Long getSEP_INFO_ID() {
		return SEP_INFO_ID;
	}

	public void setSEP_INFO_ID(Long sEP_INFO_ID) {
		SEP_INFO_ID = sEP_INFO_ID;
	}

	public Long getADMIN_TXN_ID() {
		return ADMIN_TXN_ID;
	}

	public void setADMIN_TXN_ID(Long aDMIN_TXN_ID) {
		ADMIN_TXN_ID = aDMIN_TXN_ID;
	}

	@Override
	public String toString() {
		return "ADMIN_D_P_SEP_CAYE_RATE_INFO_Mdl [SEP_CAYE_RATE_INFO_ID=" + SEP_CAYE_RATE_INFO_ID
				+ ", SEP_CAYE_RATE_UPD_TXN_ID=" + SEP_CAYE_RATE_UPD_TXN_ID + ", CAYE_APPL_YEAR=" + CAYE_APPL_YEAR
				+ ", CAYE_CON_RATE=" + CAYE_CON_RATE + ", CREATED_SOURCE=" + CREATED_SOURCE + ", CREATED_BY="
				+ CREATED_BY + ", CREATED_TS=" + CREATED_TS + ", UPDATED_SOURCE=" + UPDATED_SOURCE + ", UPDATED_BY="
				+ UPDATED_BY + ", UPDATED_TS=" + UPDATED_TS + ", NRIC=" + NRIC + ", INPUT_SOURCE=" + INPUT_SOURCE
				+ ", SEP_INFO_ID=" + SEP_INFO_ID + ", transDate=" + transDate + ", ADMIN_TXN_ID=" + ADMIN_TXN_ID + "]";
	}

}
