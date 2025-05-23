package com.home.sabir.batch.ibis.config.mapper.field;

import org.apache.commons.lang3.StringUtils;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.home.sabir.batch.ibis.constants.IBISConstants;
import com.home.sabir.batch.ibis.model.IBIS_D_R_SEP_Info_Ack_Model;

public class IBIS_D_R_SEP_Info_Ack_Field_Mapper implements FieldSetMapper<IBIS_D_R_SEP_Info_Ack_Model> {

	private static Long lineNumber = 0L;
	public static Long totalRowCount = 0L;
	
	public IBIS_D_R_SEP_Info_Ack_Field_Mapper(long totalRowCount){
		IBIS_D_R_SEP_Info_Ack_Field_Mapper.totalRowCount = totalRowCount;
	}
	@Override
	public IBIS_D_R_SEP_Info_Ack_Model mapFieldSet(FieldSet fieldSet) throws BindException {
		IBIS_D_R_SEP_Info_Ack_Model model = new IBIS_D_R_SEP_Info_Ack_Model();
		lineNumber++;
		if (lineNumber == 1l) {
			String header = getHeader(fieldSet);
			model.setHeader(header);
		} else if (lineNumber.equals(totalRowCount)) {
			String footer = getFooter(fieldSet);
			model.setFooter(footer);
		} else{
			model.setRecord_Type(fieldSet.readString("Record_Type").trim());
			model.setSelf_Employed_Person_NRIC(fieldSet.readString("Self_Employed_Person_NRIC").trim());
			model.setCAYE_Contribution_Rate(fieldSet.readString("CAYE_Contribution_Rate").trim());
			model.setError_Code(fieldSet.readString("Error_Code").trim());
			model.setError_Description(fieldSet.readString("Error_Description").trim());
			model.setFiller(fieldSet.readString("Filler").trim());
			model.setLINE_NBR(String.valueOf(lineNumber).trim());
		}

		return model;
	}

	private String getFooter(FieldSet fieldSet) {
		String footer = "";
		for (int i = 0; i <= 4; i++) {
			footer += fieldSet.getValues()[i];
		}
		footer = StringUtils.isNotBlank(footer) ? StringUtils.stripEnd(footer,IBISConstants.SPACE): "";
		return footer;
	}

	private String getHeader(FieldSet fieldSet) {
		String header = "";
		for (int i = 0; i <= 5; i++) {
			header += fieldSet.getValues()[i];
		}
		header = StringUtils.isNotBlank(header) ? StringUtils.stripEnd(header,IBISConstants.SPACE): "";
		return header;
	}

}
