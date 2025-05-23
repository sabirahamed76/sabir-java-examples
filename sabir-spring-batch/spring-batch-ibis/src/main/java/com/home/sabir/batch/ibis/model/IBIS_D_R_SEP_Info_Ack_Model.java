package com.home.sabir.batch.ibis.model;

public class IBIS_D_R_SEP_Info_Ack_Model {

	private String Record_Type;
	private String Self_Employed_Person_NRIC;
	private String CAYE_Contribution_Rate;
	private String Error_Code;
	private String Error_Description;
	private String Filler;
	private String LINE_NBR;
	private String header;
	private String footer;
	
	public String getRecord_Type() {
		return Record_Type;
	}
	public void setRecord_Type(String record_Type) {
		Record_Type = record_Type;
	}
	public String getSelf_Employed_Person_NRIC() {
		return Self_Employed_Person_NRIC;
	}
	public void setSelf_Employed_Person_NRIC(String self_Employed_Person_NRIC) {
		Self_Employed_Person_NRIC = self_Employed_Person_NRIC;
	}
	public String getCAYE_Contribution_Rate() {
		return CAYE_Contribution_Rate;
	}
	public void setCAYE_Contribution_Rate(String cAYE_Contribution_Rate) {
		CAYE_Contribution_Rate = cAYE_Contribution_Rate;
	}
	public String getError_Code() {
		return Error_Code;
	}
	public void setError_Code(String error_Code) {
		Error_Code = error_Code;
	}
	public String getError_Description() {
		return Error_Description;
	}
	public void setError_Description(String error_Description) {
		Error_Description = error_Description;
	}
	public String getFiller() {
		return Filler;
	}
	public void setFiller(String filler) {
		Filler = filler;
	}
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	public String getFooter() {
		return footer;
	}
	public void setFooter(String footer) {
		this.footer = footer;
	}
	
	public String getLINE_NBR() {
		return LINE_NBR;
	}
	public void setLINE_NBR(String lINE_NBR) {
		LINE_NBR = lINE_NBR;
	}
	@Override
	public String toString() {
		return "BANK_W_R_SEP_Info_Ack_Model [Record_Type=" + Record_Type + ", Self_Employed_Person_NRIC="
				+ Self_Employed_Person_NRIC + ", CAYE_Contribution_Rate=" + CAYE_Contribution_Rate + ", Error_Code="
				+ Error_Code + ", Error_Description=" + Error_Description + ", Filler=" + Filler + ", LINE_NBR="
				+ LINE_NBR + ", header=" + header + ", footer=" + footer + "]";
	}
		
	
}
