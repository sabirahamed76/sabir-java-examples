package com.home.sabir.batch.ibis.model;

public class IBIS_D_S_SEP_Info_Model {
	
	private String RECORD_TYPE; 
	private String SEP_NRIC;
	private String CAYE_CON_RATE;
	private String Filler;
	
	private String header;
	private String footer;
	
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
	public String getCAYE_CON_RATE() {
		return CAYE_CON_RATE;
	}
	public void setCAYE_CON_RATE(String cAYE_CON_RATE) {
		CAYE_CON_RATE = cAYE_CON_RATE;
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
	@Override
	public String toString() {
		return "SEPFDDRFileModel [RECORD_TYPE=" + RECORD_TYPE + ", SEP_NRIC=" + SEP_NRIC + ", CAYE_CON_RATE="
				+ CAYE_CON_RATE + ", Filler=" + Filler + ", header=" + header + ", footer=" + footer + "]";
	}
	
	
	

}
