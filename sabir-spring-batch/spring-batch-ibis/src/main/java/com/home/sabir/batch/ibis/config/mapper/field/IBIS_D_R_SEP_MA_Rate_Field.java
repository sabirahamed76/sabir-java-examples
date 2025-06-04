package com.home.sabir.batch.ibis.config.mapper.field;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.home.sabir.batch.ibis.model.IBIS_D_R_SEP_MA_Rate_Mdl;

public class IBIS_D_R_SEP_MA_Rate_Field implements FieldSetMapper<IBIS_D_R_SEP_MA_Rate_Mdl> {
	
	private static Long numOfRecords = 0L;
	
	public static Long lastLineCount = 0L;

	public IBIS_D_R_SEP_MA_Rate_Field(Long lastLineCount) {
		IBIS_D_R_SEP_MA_Rate_Field.lastLineCount = lastLineCount;
	}

	@Override
	public IBIS_D_R_SEP_MA_Rate_Mdl mapFieldSet(FieldSet fieldSet) throws BindException {
		IBIS_D_R_SEP_MA_Rate_Mdl model = new IBIS_D_R_SEP_MA_Rate_Mdl();
		numOfRecords++;
		
		if(numOfRecords == 1) {
			model.setHeader(fieldSet.getValues()[0] + fieldSet.getValues()[1] + fieldSet.getValues()[2] + fieldSet.getValues()[3]);
			model.setRECORD_TYPE(fieldSet.getValues()[0]);
		}
		else if(numOfRecords.equals(lastLineCount)) {
			model.setFooter(fieldSet.getValues()[0] + fieldSet.getValues()[1]);
			model.setRECORD_TYPE(fieldSet.getValues()[0]);
		}
		else {
			if(null != fieldSet.readString("RECORD_TYPE") && !fieldSet.readString("RECORD_TYPE").trim().equals(""))
				model.setRECORD_TYPE(fieldSet.readString("RECORD_TYPE").trim());
			if(null != fieldSet.readString("NRIC") && !fieldSet.readString("NRIC").trim().equals(""))
				model.setNRIC(fieldSet.readString("NRIC").trim());
			if(null != fieldSet.readString("APPL_YEAR") && !fieldSet.readString("APPL_YEAR").trim().equals(""))
				model.setAPPL_YEAR(fieldSet.readString("APPL_YEAR").trim());	
			if(null != fieldSet.readString("NTI") && !fieldSet.readString("NTI").trim().equals(""))
				model.setNTI(fieldSet.readString("NTI").trim());
			if(null != fieldSet.readString("APPL_MA_CON_RATE") && !fieldSet.readString("APPL_MA_CON_RATE").trim().equals(""))
				model.setAPPL_MA_CON_RATE(fieldSet.readString("APPL_MA_CON_RATE").trim());
			if(null != fieldSet.readString("MAX_APPL_MA_CON_RATE") && !fieldSet.readString("MAX_APPL_MA_CON_RATE").trim().equals(""))
				model.setMAX_APPL_MA_CON_RATE(fieldSet.readString("MAX_APPL_MA_CON_RATE").trim());
			if(null != fieldSet.readString("MAX_APPL_MA_CON_AMOUNT") && !fieldSet.readString("MAX_APPL_MA_CON_AMOUNT").trim().equals(""))
				model.setMAX_APPL_MA_CON_AMOUNT(fieldSet.readString("MAX_APPL_MA_CON_AMOUNT").trim());
		}
		return model;
	}

}
