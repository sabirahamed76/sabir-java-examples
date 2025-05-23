package com.home.sabir.batch.ibis.config.processor;

import static com.home.sabir.batch.ibis.constants.IBISConstants.COLUMN_FOOTER;
import static com.home.sabir.batch.ibis.constants.IBISConstants.COLUMN_FOOTER_NOT;
import static com.home.sabir.batch.ibis.constants.IBISConstants.COLUMN_HEADER;
import static com.home.sabir.batch.ibis.constants.IBISConstants.COLUMN_HEADER_NOT;
import static com.home.sabir.batch.ibis.constants.IBISConstants.COLUMN_RECORD;
import static com.home.sabir.batch.ibis.constants.IBISConstants.ERROR_CODE_MSG;
import static com.home.sabir.batch.ibis.constants.IBISConstants.ERROR_DESC_MSG;
import static com.home.sabir.batch.ibis.constants.IBISConstants.FOOTER_RECORD_TYPE;
import static com.home.sabir.batch.ibis.constants.IBISConstants.HEADER_ERROR;
import static com.home.sabir.batch.ibis.constants.IBISConstants.HEADER_RECORD_TYPE;
import static com.home.sabir.batch.ibis.constants.IBISConstants.NRIC_COLUMN;
import static com.home.sabir.batch.ibis.constants.IBISConstants.RECORD_TYPE_MSG_FOOTER;
import static com.home.sabir.batch.ibis.constants.IBISConstants.RECORD_TYPE_MSG_HEADER;
import static com.home.sabir.batch.ibis.constants.IBISConstants.RECORD_TYPE_MSG_ROW;
import static com.home.sabir.batch.ibis.constants.IBISConstants.SuccessfulRecords;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.item.ItemProcessor;

import com.home.sabir.batch.core.model.BatchJobExceptionModel;
import com.home.sabir.batch.ibis.model.IBIS_D_R_SEP_Info_Ack_Model;
import com.home.sabir.batch.ibis.utils.JobUtils;

public class IBIS_D_R_SEP_Info_Ack_Val_Processor
implements ItemProcessor<IBIS_D_R_SEP_Info_Ack_Model, IBIS_D_R_SEP_Info_Ack_Model> {
	private static final Logger logger = LogManager.getLogger(IBIS_D_R_SEP_Info_Ack_Val_Processor.class);
	public static Long currentRowNumber = 0L;
	public long totalFileCount = 0;
	public static List<BatchJobExceptionModel> exceptionModelList = new ArrayList<BatchJobExceptionModel>();
	public boolean errorExists;
	BatchJobExceptionModel exceptionModel;

	public IBIS_D_R_SEP_Info_Ack_Val_Processor(long totalRecordCount) {
		this.totalFileCount = totalRecordCount;
		logger.info("SEPInfoAck IBIS_D_R_SEP_Info_Ack_Val_Processor totalRecordCount:" + totalRecordCount);
	}

	@Override
	public IBIS_D_R_SEP_Info_Ack_Model process(IBIS_D_R_SEP_Info_Ack_Model item) throws Exception {
		currentRowNumber++;
		if (currentRowNumber == 1l) {
			if(isNull(item)) {
				exceptionModelList.add(JobUtils.formExceptionModel(COLUMN_HEADER, RECORD_TYPE_MSG_HEADER, currentRowNumber, null, null, COLUMN_HEADER));
				return null;
			}
			validateHeader(item.getHeader(), currentRowNumber);
		} else if (currentRowNumber.equals(totalFileCount)) {
			if(isNull(item)) {
				exceptionModelList.add(JobUtils.formExceptionModel(COLUMN_FOOTER, RECORD_TYPE_MSG_FOOTER, currentRowNumber, null, null, COLUMN_FOOTER));
				return null;
			}

			String footer = item.getFooter();
			try {
				if(!com.home.sabir.batch.core.util.StringUtils.isValid(footer.substring(0, 3)) || 
						(com.home.sabir.batch.core.util.StringUtils.isValid(footer.substring(0, 3)) && !FOOTER_RECORD_TYPE.equals(footer.substring(0, 3).trim())))
					exceptionModelList.add(JobUtils.formExceptionModel(COLUMN_FOOTER, COLUMN_FOOTER_NOT, currentRowNumber, null, null, COLUMN_FOOTER));
			} catch (Exception e) {
				exceptionModelList.add(JobUtils.formExceptionModel(COLUMN_FOOTER, COLUMN_FOOTER_NOT, currentRowNumber, null, null, COLUMN_FOOTER));
			}
			return null;
		} else {
			if(isNull(item)) {
				exceptionModelList.add(JobUtils.formExceptionModel(COLUMN_RECORD, RECORD_TYPE_MSG_ROW, currentRowNumber, null, null, COLUMN_RECORD));
				return null;
			}
			exceptionModel =	JobUtils.formExceptionModel(NRIC_COLUMN, item.getError_Description(), currentRowNumber, item.getSelf_Employed_Person_NRIC(), NRIC_COLUMN, COLUMN_RECORD);
			exceptionModelList.add(exceptionModel);
		}

		return item;
	}


	private void validateHeader(String header, long readCount) {
		logger.info("SEPInfoAck IBIS_D_R_SEP_Info_Ack_Val_Processor.validateHeader Details Line Number:" + readCount
				+ "-Header:" + header);
		try {
			String recordType = "", errorCode = "", errorDesc = "";

			try {
				if(!com.home.sabir.batch.core.util.StringUtils.isValid(header.substring(0, 3)) || 
						(com.home.sabir.batch.core.util.StringUtils.isValid(header.substring(0, 3)) && !HEADER_RECORD_TYPE.equals(header.substring(0, 3).trim())))
					exceptionModelList.add(JobUtils.formExceptionModel(COLUMN_HEADER, COLUMN_HEADER_NOT, currentRowNumber, null, null, COLUMN_HEADER));
				recordType = header.substring(0, 3).trim();
			} catch (Exception e) {
				exceptionModelList.add(JobUtils.formExceptionModel(COLUMN_HEADER, COLUMN_HEADER_NOT, currentRowNumber, null, null, COLUMN_HEADER));
			}

			try {
				if(!com.home.sabir.batch.core.util.StringUtils.isValid(header.substring(12, 15)))
					exceptionModelList.add(JobUtils.formExceptionModel(COLUMN_HEADER, ERROR_CODE_MSG, currentRowNumber, null, null, COLUMN_HEADER));
				errorCode = header.substring(12, 15).trim();
			} catch (Exception e) {
				exceptionModelList.add(JobUtils.formExceptionModel(COLUMN_HEADER, ERROR_CODE_MSG, currentRowNumber, null, null, COLUMN_HEADER));
			}

			try {
				if(!com.home.sabir.batch.core.util.StringUtils.isValid(header.substring(15)))
					exceptionModelList.add(JobUtils.formExceptionModel(COLUMN_HEADER, ERROR_DESC_MSG, currentRowNumber, null, null, COLUMN_HEADER));
				errorDesc = header.substring(15).trim();
			} catch (Exception e) {
				exceptionModelList.add(JobUtils.formExceptionModel(COLUMN_HEADER, ERROR_DESC_MSG, currentRowNumber, null, null, COLUMN_HEADER));
			}

			if(!SuccessfulRecords.equals(errorCode)){
				exceptionModel =	JobUtils.formExceptionModel(COLUMN_HEADER, errorDesc, currentRowNumber, recordType, null, COLUMN_HEADER)	;
				exceptionModelList.add(exceptionModel);
				errorExists = true;
			}

		} catch (Exception e) {
			exceptionModel =	JobUtils.formExceptionModel(COLUMN_HEADER, HEADER_ERROR, currentRowNumber,HEADER_RECORD_TYPE, null, COLUMN_HEADER)	;
			exceptionModelList.add(exceptionModel);
		}
	}

	private boolean isNull(IBIS_D_R_SEP_Info_Ack_Model obj) {
		try {
			return Stream.of(obj.getRecord_Type(), obj.getSelf_Employed_Person_NRIC(), obj.getCAYE_Contribution_Rate(), obj.getError_Code(), obj.getError_Description(), obj.getHeader(), obj.getFooter())
					.allMatch(Objects::isNull);
		} catch (Exception e) {
			String recType = currentRowNumber == 1 ? COLUMN_HEADER : (currentRowNumber.equals(totalFileCount) ? COLUMN_FOOTER : COLUMN_RECORD);
			exceptionModelList.add(JobUtils.formExceptionModel(recType, "Record is not valid", currentRowNumber,null, null, recType));
			return true;
		}
	}
}
