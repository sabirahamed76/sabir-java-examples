package com.home.sabir.batch.ibis.model;

public class IBIS_D_R_SEP_MA_Rate_Mdl {

	private String RECORD_TYPE;
	private String NRIC;
	private String NTI;
	private String APPL_MA_CON_RATE;
	private String MAX_APPL_MA_CON_RATE;
	private String MAX_APPL_MA_CON_AMOUNT;
	
	private String header;
	private String footer;

	private String CREATE_TS;
	private String APPL_YEAR;
	private Integer RELEVANT_YEAR;
	private Double CAYE_CON_RATE;
	
	
	private Long SEP_IRAS_TXN_ID;
	private Long SEP_IRAS_INFO_ID;
	private Long SEP_INFO_ID;
	private Double MA_CON_RATE;
	private Double MAX_MA_CON_RATE;
	private Double MAX_MA_CON_AMOUNT;
	private Double MA_CON_AMOUNT;
	private Double IRAS_REVENUE;
	private Double IRAS_NTI;
	
	private Long SEP_CAYE_RATE_INFO_ID;
	private String FILE_TYPE;

	public Long getSEP_INFO_ID() {
		return SEP_INFO_ID;
	}
	public void setSEP_INFO_ID(Long sEP_INFO_ID) {
		SEP_INFO_ID = sEP_INFO_ID;
	}
	public String getRECORD_TYPE() {
		return RECORD_TYPE;
	}
	public void setRECORD_TYPE(String rECORD_TYPE) {
		RECORD_TYPE = rECORD_TYPE;
	}
	public String getNRIC() {
		return NRIC;
	}
	public void setNRIC(String nRIC) {
		NRIC = nRIC;
	}
	public String getNTI() {
		return NTI;
	}
	public void setNTI(String nTI) {
		NTI = nTI;
	}
	public String getAPPL_MA_CON_RATE() {
		return APPL_MA_CON_RATE;
	}
	public void setAPPL_MA_CON_RATE(String aPPL_MA_CON_RATE) {
		APPL_MA_CON_RATE = aPPL_MA_CON_RATE;
	}
	public String getMAX_APPL_MA_CON_RATE() {
		return MAX_APPL_MA_CON_RATE;
	}
	public void setMAX_APPL_MA_CON_RATE(String mAX_APPL_MA_CON_RATE) {
		MAX_APPL_MA_CON_RATE = mAX_APPL_MA_CON_RATE;
	}
	public String getCREATE_TS() {
		return CREATE_TS;
	}
	public void setCREATE_TS(String cREATE_TS) {
		CREATE_TS = cREATE_TS;
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
	public String getAPPL_YEAR() {
		return APPL_YEAR;
	}
	public void setAPPL_YEAR(String aPPL_YEAR) {
		APPL_YEAR = aPPL_YEAR;
	}
	public Integer getRELEVANT_YEAR() {
		return RELEVANT_YEAR;
	}
	public void setRELEVANT_YEAR(Integer rELEVANT_YEAR) {
		RELEVANT_YEAR = rELEVANT_YEAR;
	}
	public Double getCAYE_CON_RATE() {
		return CAYE_CON_RATE;
	}
	public void setCAYE_CON_RATE(Double cAYE_CON_RATE) {
		CAYE_CON_RATE = cAYE_CON_RATE;
	}
	
	public String getMAX_APPL_MA_CON_AMOUNT() {
		return MAX_APPL_MA_CON_AMOUNT;
	}
	public void setMAX_APPL_MA_CON_AMOUNT(String mAX_APPL_MA_CON_AMOUNT) {
		MAX_APPL_MA_CON_AMOUNT = mAX_APPL_MA_CON_AMOUNT;
	}
	public Long getSEP_IRAS_TXN_ID() {
		return SEP_IRAS_TXN_ID;
	}
	public void setSEP_IRAS_TXN_ID(Long sEP_IRAS_TXN_ID) {
		SEP_IRAS_TXN_ID = sEP_IRAS_TXN_ID;
	}
	public Long getSEP_IRAS_INFO_ID() {
		return SEP_IRAS_INFO_ID;
	}
	public void setSEP_IRAS_INFO_ID(Long sEP_IRAS_INFO_ID) {
		SEP_IRAS_INFO_ID = sEP_IRAS_INFO_ID;
	}
	
	public Double getMA_CON_AMOUNT() {
		return MA_CON_AMOUNT;
	}
	public void setMA_CON_AMOUNT(Double mA_CON_AMOUNT) {
		MA_CON_AMOUNT = mA_CON_AMOUNT;
	}
	public Double getMA_CON_RATE() {
		return MA_CON_RATE;
	}
	public void setMA_CON_RATE(Double mA_CON_RATE) {
		MA_CON_RATE = mA_CON_RATE;
	}
	public Double getMAX_MA_CON_RATE() {
		return MAX_MA_CON_RATE;
	}
	public void setMAX_MA_CON_RATE(Double mAX_MA_CON_RATE) {
		MAX_MA_CON_RATE = mAX_MA_CON_RATE;
	}
	public Double getMAX_MA_CON_AMOUNT() {
		return MAX_MA_CON_AMOUNT;
	}
	public void setMAX_MA_CON_AMOUNT(Double mAX_MA_CON_AMOUNT) {
		MAX_MA_CON_AMOUNT = mAX_MA_CON_AMOUNT;
	}
	public Double getIRAS_REVENUE() {
		return IRAS_REVENUE;
	}
	public void setIRAS_REVENUE(Double iRAS_REVENUE) {
		IRAS_REVENUE = iRAS_REVENUE;
	}
	public Double getIRAS_NTI() {
		return IRAS_NTI;
	}
	public void setIRAS_NTI(Double iRAS_NTI) {
		IRAS_NTI = iRAS_NTI;
	}
	public Long getSEP_CAYE_RATE_INFO_ID() {
		return SEP_CAYE_RATE_INFO_ID;
	}
	public void setSEP_CAYE_RATE_INFO_ID(Long sEP_CAYE_RATE_INFO_ID) {
		SEP_CAYE_RATE_INFO_ID = sEP_CAYE_RATE_INFO_ID;
	}
	public String getFILE_TYPE() {
		return FILE_TYPE;
	}
	public void setFILE_TYPE(String fILE_TYPE) {
		FILE_TYPE = fILE_TYPE;
	}
	
	/*@Override
	public String toString() {
		return "SEM_Y_R_SEP_MA_Rate_Mdl [RECORD_TYPE=" + RECORD_TYPE + ", NRIC=" + NRIC + ", NTI=" + NTI
				+ ", APPL_MA_CON_RATE=" + APPL_MA_CON_RATE + ", MAX_APPL_MA_CON_RATE=" + MAX_APPL_MA_CON_RATE
				+ ", MAX_APPL_MA_CON_AMOUNT=" + MAX_APPL_MA_CON_AMOUNT + ", header=" + header + ", footer=" + footer
				+ ", CREATE_TS=" + CREATE_TS + ", APPL_YEAR=" + APPL_YEAR + ", RELEVANT_YEAR=" + RELEVANT_YEAR
				+ ", CAYE_CON_RATE=" + CAYE_CON_RATE + ", SEP_IRAS_TXN_ID=" + SEP_IRAS_TXN_ID + ", SEP_IRAS_INFO_ID="
				+ SEP_IRAS_INFO_ID + ", SEP_INFO_ID=" + SEP_INFO_ID + ", MA_CON_RATE=" + MA_CON_RATE
				+ ", MAX_MA_CON_RATE=" + MAX_MA_CON_RATE + ", MAX_MA_CON_AMOUNT=" + MAX_MA_CON_AMOUNT
				+ ", MA_CON_AMOUNT=" + MA_CON_AMOUNT + ", IRAS_REVENUE=" + IRAS_REVENUE + ", IRAS_NTI=" + IRAS_NTI
				+ ", SEP_CAYE_RATE_INFO_ID=" + SEP_CAYE_RATE_INFO_ID + ", FILE_TYPE=" + FILE_TYPE + "]";
	}*/
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((APPL_MA_CON_RATE == null) ? 0 : APPL_MA_CON_RATE.hashCode());
		result = prime * result + ((APPL_YEAR == null) ? 0 : APPL_YEAR.hashCode());
		result = prime * result + ((CAYE_CON_RATE == null) ? 0 : CAYE_CON_RATE.hashCode());
		result = prime * result + ((CREATE_TS == null) ? 0 : CREATE_TS.hashCode());
		result = prime * result + ((FILE_TYPE == null) ? 0 : FILE_TYPE.hashCode());
		result = prime * result + ((IRAS_NTI == null) ? 0 : IRAS_NTI.hashCode());
		result = prime * result + ((IRAS_REVENUE == null) ? 0 : IRAS_REVENUE.hashCode());
		result = prime * result + ((MAX_APPL_MA_CON_AMOUNT == null) ? 0 : MAX_APPL_MA_CON_AMOUNT.hashCode());
		result = prime * result + ((MAX_APPL_MA_CON_RATE == null) ? 0 : MAX_APPL_MA_CON_RATE.hashCode());
		result = prime * result + ((MAX_MA_CON_AMOUNT == null) ? 0 : MAX_MA_CON_AMOUNT.hashCode());
		result = prime * result + ((MAX_MA_CON_RATE == null) ? 0 : MAX_MA_CON_RATE.hashCode());
		result = prime * result + ((MA_CON_AMOUNT == null) ? 0 : MA_CON_AMOUNT.hashCode());
		result = prime * result + ((MA_CON_RATE == null) ? 0 : MA_CON_RATE.hashCode());
		result = prime * result + ((NRIC == null) ? 0 : NRIC.hashCode());
		result = prime * result + ((NTI == null) ? 0 : NTI.hashCode());
		result = prime * result + ((RECORD_TYPE == null) ? 0 : RECORD_TYPE.hashCode());
		result = prime * result + ((RELEVANT_YEAR == null) ? 0 : RELEVANT_YEAR.hashCode());
		result = prime * result + ((SEP_CAYE_RATE_INFO_ID == null) ? 0 : SEP_CAYE_RATE_INFO_ID.hashCode());
		result = prime * result + ((SEP_INFO_ID == null) ? 0 : SEP_INFO_ID.hashCode());
		result = prime * result + ((SEP_IRAS_INFO_ID == null) ? 0 : SEP_IRAS_INFO_ID.hashCode());
		result = prime * result + ((SEP_IRAS_TXN_ID == null) ? 0 : SEP_IRAS_TXN_ID.hashCode());
		result = prime * result + ((footer == null) ? 0 : footer.hashCode());
		result = prime * result + ((header == null) ? 0 : header.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IBIS_D_R_SEP_MA_Rate_Mdl other = (IBIS_D_R_SEP_MA_Rate_Mdl) obj;
		if (APPL_MA_CON_RATE == null) {
			if (other.APPL_MA_CON_RATE != null)
				return false;
		} else if (!APPL_MA_CON_RATE.equals(other.APPL_MA_CON_RATE))
			return false;
		if (APPL_YEAR == null) {
			if (other.APPL_YEAR != null)
				return false;
		} else if (!APPL_YEAR.equals(other.APPL_YEAR))
			return false;
		if (CAYE_CON_RATE == null) {
			if (other.CAYE_CON_RATE != null)
				return false;
		} else if (!CAYE_CON_RATE.equals(other.CAYE_CON_RATE))
			return false;
		if (CREATE_TS == null) {
			if (other.CREATE_TS != null)
				return false;
		} else if (!CREATE_TS.equals(other.CREATE_TS))
			return false;
		if (FILE_TYPE == null) {
			if (other.FILE_TYPE != null)
				return false;
		} else if (!FILE_TYPE.equals(other.FILE_TYPE))
			return false;
		if (IRAS_NTI == null) {
			if (other.IRAS_NTI != null)
				return false;
		} else if (!IRAS_NTI.equals(other.IRAS_NTI))
			return false;
		if (IRAS_REVENUE == null) {
			if (other.IRAS_REVENUE != null)
				return false;
		} else if (!IRAS_REVENUE.equals(other.IRAS_REVENUE))
			return false;
		if (MAX_APPL_MA_CON_AMOUNT == null) {
			if (other.MAX_APPL_MA_CON_AMOUNT != null)
				return false;
		} else if (!MAX_APPL_MA_CON_AMOUNT.equals(other.MAX_APPL_MA_CON_AMOUNT))
			return false;
		if (MAX_APPL_MA_CON_RATE == null) {
			if (other.MAX_APPL_MA_CON_RATE != null)
				return false;
		} else if (!MAX_APPL_MA_CON_RATE.equals(other.MAX_APPL_MA_CON_RATE))
			return false;
		if (MAX_MA_CON_AMOUNT == null) {
			if (other.MAX_MA_CON_AMOUNT != null)
				return false;
		} else if (!MAX_MA_CON_AMOUNT.equals(other.MAX_MA_CON_AMOUNT))
			return false;
		if (MAX_MA_CON_RATE == null) {
			if (other.MAX_MA_CON_RATE != null)
				return false;
		} else if (!MAX_MA_CON_RATE.equals(other.MAX_MA_CON_RATE))
			return false;
		if (MA_CON_AMOUNT == null) {
			if (other.MA_CON_AMOUNT != null)
				return false;
		} else if (!MA_CON_AMOUNT.equals(other.MA_CON_AMOUNT))
			return false;
		if (MA_CON_RATE == null) {
			if (other.MA_CON_RATE != null)
				return false;
		} else if (!MA_CON_RATE.equals(other.MA_CON_RATE))
			return false;
		if (NRIC == null) {
			if (other.NRIC != null)
				return false;
		} else if (!NRIC.equals(other.NRIC))
			return false;
		if (NTI == null) {
			if (other.NTI != null)
				return false;
		} else if (!NTI.equals(other.NTI))
			return false;
		if (RECORD_TYPE == null) {
			if (other.RECORD_TYPE != null)
				return false;
		} else if (!RECORD_TYPE.equals(other.RECORD_TYPE))
			return false;
		if (RELEVANT_YEAR == null) {
			if (other.RELEVANT_YEAR != null)
				return false;
		} else if (!RELEVANT_YEAR.equals(other.RELEVANT_YEAR))
			return false;
		if (SEP_CAYE_RATE_INFO_ID == null) {
			if (other.SEP_CAYE_RATE_INFO_ID != null)
				return false;
		} else if (!SEP_CAYE_RATE_INFO_ID.equals(other.SEP_CAYE_RATE_INFO_ID))
			return false;
		if (SEP_INFO_ID == null) {
			if (other.SEP_INFO_ID != null)
				return false;
		} else if (!SEP_INFO_ID.equals(other.SEP_INFO_ID))
			return false;
		if (SEP_IRAS_INFO_ID == null) {
			if (other.SEP_IRAS_INFO_ID != null)
				return false;
		} else if (!SEP_IRAS_INFO_ID.equals(other.SEP_IRAS_INFO_ID))
			return false;
		if (SEP_IRAS_TXN_ID == null) {
			if (other.SEP_IRAS_TXN_ID != null)
				return false;
		} else if (!SEP_IRAS_TXN_ID.equals(other.SEP_IRAS_TXN_ID))
			return false;
		if (footer == null) {
			if (other.footer != null)
				return false;
		} else if (!footer.equals(other.footer))
			return false;
		if (header == null) {
			if (other.header != null)
				return false;
		} else if (!header.equals(other.header))
			return false;
		return true;
	}
}
