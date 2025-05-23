package com.home.sabir.batch.core.model;

import java.util.Date;

public class BatchJobExceptionModel {

	private Integer BATCH_JOB_EXCEPTION_ID;
	private Integer BATCH_JOB_ID;
	private Date EXECUTION_DATE;
	private Long LINE_NBR;	
	private String COLUMN_NAME;
	private String ERROR_CODE;
	private String REMARKS;
	private String COLUMN_VALUE;
	private String REJECTION_TYPE;
	private String REF_KEY_NAMES;
	private String 	REF_KEY_VALUES;
	private String RECORD_TYPE;
	
	private String EXECUTION_DATE_STR;
	
	public Integer getBATCH_JOB_EXCEPTION_ID() {
		return BATCH_JOB_EXCEPTION_ID;
	}
	public void setBATCH_JOB_EXCEPTION_ID(Integer bATCH_JOB_EXCEPTION_ID) {
		BATCH_JOB_EXCEPTION_ID = bATCH_JOB_EXCEPTION_ID;
	}
	public Integer getBATCH_JOB_ID() {
		return BATCH_JOB_ID;
	}
	public void setBATCH_JOB_ID(Integer bATCH_JOB_ID) {
		BATCH_JOB_ID = bATCH_JOB_ID;
	}
	public Date getEXECUTION_DATE() {
		return EXECUTION_DATE;
	}
	public void setEXECUTION_DATE(Date eXECUTION_DATE) {
		EXECUTION_DATE = eXECUTION_DATE;
	}
	public Long getLINE_NBR() {
		return LINE_NBR;
	}
	public void setLINE_NBR(Long lINE_NBR) {
		LINE_NBR = lINE_NBR;
	}
	public String getCOLUMN_NAME() {
		return COLUMN_NAME;
	}
	public void setCOLUMN_NAME(String cOLUMN_NAME) {
		COLUMN_NAME = cOLUMN_NAME;
	}
	public String getERROR_CODE() {
		return ERROR_CODE;
	}
	public void setERROR_CODE(String eRROR_CODE) {
		ERROR_CODE = eRROR_CODE;
	}
	public String getREMARKS() {
		return REMARKS;
	}
	public void setREMARKS(String rEMARKS) {
		REMARKS = rEMARKS;
	}	
	public String getCOLUMN_VALUE() {
		return COLUMN_VALUE;
	}
	public void setCOLUMN_VALUE(String cOLUMN_VALUE) {
		COLUMN_VALUE = cOLUMN_VALUE;
	}
	public String getREJECTION_TYPE() {
		return REJECTION_TYPE;
	}
	public void setREJECTION_TYPE(String rEJECTION_TYPE) {
		REJECTION_TYPE = rEJECTION_TYPE;
	}
	public String getEXECUTION_DATE_STR() {
		return EXECUTION_DATE_STR;
	}
	public void setEXECUTION_DATE_STR(String eXECUTION_DATE_STR) {
		EXECUTION_DATE_STR = eXECUTION_DATE_STR;
	}
	public String getREF_KEY_NAMES() {
		return REF_KEY_NAMES;
	}
	public void setREF_KEY_NAMES(String rEF_KEY_NAMES) {
		REF_KEY_NAMES = rEF_KEY_NAMES;
	}
	public String getREF_KEY_VALUES() {
		return REF_KEY_VALUES;
	}
	public void setREF_KEY_VALUES(String rEF_KEY_VALUES) {
		REF_KEY_VALUES = rEF_KEY_VALUES;
	}
	public String getRECORD_TYPE() {
		return RECORD_TYPE;
	}
	public void setRECORD_TYPE(String rECORD_TYPE) {
		RECORD_TYPE = rECORD_TYPE;
	}
	
	@Override
	public String toString() {
		return "BatchJobExceptionModel [BATCH_JOB_EXCEPTION_ID=" + BATCH_JOB_EXCEPTION_ID + ", BATCH_JOB_ID="
				+ BATCH_JOB_ID + ", EXECUTION_DATE=" + EXECUTION_DATE + ", LINE_NBR=" + LINE_NBR + ", COLUMN_NAME="
				+ COLUMN_NAME + ", ERROR_CODE=" + ERROR_CODE + ", REMARKS=" + REMARKS + ", COLUMN_VALUE=" + COLUMN_VALUE
				+ ", REJECTION_TYPE=" + REJECTION_TYPE + ", REF_KEY_NAMES=" + REF_KEY_NAMES + ", REF_KEY_VALUES="
				+ REF_KEY_VALUES + ", RECORD_TYPE=" + RECORD_TYPE + ", EXECUTION_DATE_STR=" + EXECUTION_DATE_STR + "]";
	}
}
