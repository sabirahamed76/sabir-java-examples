package com.home.sabir.batch.core.model;

public class BatchJobEmailModel {

	private Integer ADMIN_EMAIL_TEMPL_INFO_ID;
	private Integer ADMIN_FUNCTION_INFO_ID;
	private String SUBJECT;
	private String EMAIL_MSG;
	private String EMAIL_TO;
	private String TRANS_NBR;
	
	public Integer getADMIN_EMAIL_TEMPL_INFO_ID() {
		return ADMIN_EMAIL_TEMPL_INFO_ID;
	}
	public void setADMIN_EMAIL_TEMPL_INFO_ID(Integer aDMIN_EMAIL_TEMPL_INFO_ID) {
		ADMIN_EMAIL_TEMPL_INFO_ID = aDMIN_EMAIL_TEMPL_INFO_ID;
	}
	public String getEMAIL_MSG() {
		return EMAIL_MSG;
	}
	public void setEMAIL_MSG(String eMAIL_MSG) {
		EMAIL_MSG = eMAIL_MSG;
	}	
	public Integer getADMIN_FUNCTION_INFO_ID() {
		return ADMIN_FUNCTION_INFO_ID;
	}
	public void setADMIN_FUNCTION_INFO_ID(Integer aDMIN_FUNCTION_INFO_ID) {
		ADMIN_FUNCTION_INFO_ID = aDMIN_FUNCTION_INFO_ID;
	}
	public String getSUBJECT() {
		return SUBJECT;
	}
	public void setSUBJECT(String sUBJECT) {
		SUBJECT = sUBJECT;
	}
	public String getEMAIL_TO() {
		return EMAIL_TO;
	}
	public void setEMAIL_TO(String eMAIL_TO) {
		EMAIL_TO = eMAIL_TO;
	}
	public String getTRANS_NBR() {
		return TRANS_NBR;
	}
	public void setTRANS_NBR(String tRANS_NBR) {
		TRANS_NBR = tRANS_NBR;
	}
	
	@Override
	public String toString() {
		return "BatchJobEmailModel [ADMIN_EMAIL_TEMPL_INFO_ID=" + ADMIN_EMAIL_TEMPL_INFO_ID
				+ ", ADMIN_FUNCTION_INFO_ID=" + ADMIN_FUNCTION_INFO_ID + ", SUBJECT=" + SUBJECT + ", EMAIL_MSG="
				+ EMAIL_MSG + ", EMAIL_TO=" + EMAIL_TO + ", TRANS_NBR=" + TRANS_NBR + "]";
	}
}
