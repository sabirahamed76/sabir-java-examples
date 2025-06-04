package com.home.sabir.batch.ibis.config.processor;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.jdbc.core.JdbcTemplate;

import com.home.sabir.batch.core.util.DateUtils;
import com.home.sabir.batch.core.util.NumberUtils;
import com.home.sabir.batch.core.util.StringUtils;
import com.home.sabir.batch.ibis.config.mapper.field.IBIS_D_R_SEP_MA_Rate_Field;
import com.home.sabir.batch.ibis.model.IBIS_D_R_SEP_MA_Rate_Mdl;

public class IBIS_D_R_SEP_MA_Rate_Validation_Processor implements ItemProcessor<IBIS_D_R_SEP_MA_Rate_Mdl, IBIS_D_R_SEP_MA_Rate_Mdl> {

	private static final Logger logger = LogManager.getLogger(IBIS_D_R_SEP_MA_Rate_Validation_Processor.class);

	public static Long readCount = 0L;
	public static List<String> errorList = new ArrayList<>();
	private Set<String> nricSet = new HashSet<>();
	private static List<String> nricList = new ArrayList<>();
	private static List<String> irasNRICList = new ArrayList<>();
	List<String> comparisonList = new ArrayList<>();
	private JdbcTemplate jdbcTemplate;

	public IBIS_D_R_SEP_MA_Rate_Validation_Processor(DataSource dataSource) {		
		jdbcTemplate = new JdbcTemplate(dataSource);
		irasNRICList = jdbcTemplate.queryForList("SELECT DISTINCT NRIC FROM STG_IBIS_D_S_SEP_IRAS_NTI", String.class);
	}

	@Override
	public IBIS_D_R_SEP_MA_Rate_Mdl process(IBIS_D_R_SEP_MA_Rate_Mdl model) throws Exception {
		logger.info("*****IBIS_D_R_SEP_MA_Rate_Validation_Processor started*****");
		List<String> errorList = new ArrayList<>();
		String dateH = "", prgmIDH = "", recCountF = "";
		int index = 0;
		readCount++;

		if(readCount == 1) {
			if(isNull(model)) {
				errorList.add("HEADER is not present at the start of the file~" + readCount + "~null~null~HEADER~HEADER");
				IBIS_D_R_SEP_MA_Rate_Validation_Processor.errorList.addAll(errorList);
				return model;
			} else {
				if(!StringUtils.isValid(model.getRECORD_TYPE()) || (StringUtils.isValid(model.getRECORD_TYPE()) && !model.getRECORD_TYPE().equals("0"))) {
					errorList.add("HEADER not found~" + readCount + "~" + model.getRECORD_TYPE() + "~null~HEADER~HEADER");
					IBIS_D_R_SEP_MA_Rate_Validation_Processor.errorList.addAll(errorList);
					return model;
				}

				try {
					if(!StringUtils.isValid(model.getHeader().substring(1, 9)))
						errorList.add("CREATED_DATE in HEADER is empty~" + readCount + "~null~null~HEADER~CREATED_DATE");
					dateH = model.getHeader().substring(1, 9).trim();
				} catch (Exception e) {
					errorList.add("CREATED_DATE in HEADER is empty~" + readCount + "~null~null~HEADER~CREATED_DATE");
				}
				try {
					index = StringUtils.isValid(dateH) ? 9 : 1;
					if(!StringUtils.isValid(model.getHeader().substring(index)))
						errorList.add("PROGRAM_ID in HEADER is empty~" + readCount + "~null~null~HEADER~PROGRAM_ID");
					prgmIDH = model.getHeader().substring(index).trim();
				} catch (Exception e) {
					errorList.add("PROGRAM_ID in HEADER is empty~" + readCount + "~null~null~HEADER~PROGRAM_ID");
				}
				try {
					if (StringUtils.isValid(dateH)) {
						if(!DateUtils.isHeaderDateValid(dateH,DateUtils.DATE_FORMAT_YYYYMMDD))
							errorList.add("CREATED_DATE in HEADER should be a valid date~" + readCount + "~" + dateH + "~null~HEADER~CREATED_DATE");
						else {
							DateTimeFormatter  dateFormat = DateTimeFormatter.ofPattern(DateUtils.DATE_FORMAT_YYYYMMDD);
							LocalDate date = LocalDate.now();
							if(!dateH.equals(dateFormat.format(date)))
								errorList.add("CREATED_DATE in HEADER is not today's date~" + readCount + "~" + dateH + "~null~HEADER~CREATED_DATE");
						}
					}
				} catch (Exception e) {
					errorList.add("CREATED_DATE in HEADER should be a valid date~" + readCount + "~" + model.getHeader() + "~null~HEADER~CREATED_DATE");
				}
				if(StringUtils.isValid(prgmIDH) && !prgmIDH.equals("SEMA098"))
					errorList.add("PROGRAM_ID in HEADER should be 'SEMA098'~" + readCount + "~" + prgmIDH + "~null~HEADER~PROGRAM_ID");
			}
		}			

		//if its not the last line - it is treated as a Detail record
		if(readCount != 1 && !IBIS_D_R_SEP_MA_Rate_Field.lastLineCount.equals(readCount)) {
			if(!isNull(model)) {
				String nric = "";
				if(StringUtils.isValid(model.getNRIC()))
					nric = "~" + model.getNRIC() + "~DETAIL";
				else nric = "~null~DETAIL";

				//ignore other validations if RECORD_TYPE is invalid
				if(!StringUtils.isValid(model.getRECORD_TYPE()) || (StringUtils.isValid(model.getRECORD_TYPE()) && !model.getRECORD_TYPE().trim().equals("1"))) {
					errorList.add("RECORD_TYPE is invalid~" + readCount + "~" + model.getRECORD_TYPE() + nric + "~RECORD_TYPE");
					IBIS_D_R_SEP_MA_Rate_Validation_Processor.errorList.addAll(errorList);
					return model;
				}

				//Null & Empty validations for mandatory fields
				if(!StringUtils.isValid(model.getNRIC()))
					errorList.add("NRIC is empty~" + readCount + "~null" + nric + "~NRIC");
				if(!StringUtils.isValid(model.getAPPL_YEAR()))
					errorList.add("APPL_YEAR is empty~" + readCount + "~null" + nric + "~APPL_YEAR");
				if(!StringUtils.isValid(model.getNTI()))
					errorList.add("NTI is empty~" + readCount + "~null" + nric + "~NTI");
				if(!StringUtils.isValid(model.getAPPL_MA_CON_RATE()))
					errorList.add("APPL_MA_CON_RATE is empty~" + readCount + "~null" + nric + "~APPL_MA_CON_RATE");
				if(!StringUtils.isValid(model.getMAX_APPL_MA_CON_RATE()))
					errorList.add("MAX_APPL_MA_CON_RATE is empty~" + readCount + "~null" + nric + "~MAX_APPL_MA_CON_RATE");
				if(!StringUtils.isValid(model.getMAX_APPL_MA_CON_AMOUNT()))
					errorList.add("MAX_APPL_MA_CON_AMOUNT is empty~" + readCount + "~null" + nric + "~MAX_APPL_MA_CON_AMOUNT");

				if(StringUtils.isValid(model.getNRIC())) {
					if(!nricSet.add(model.getNRIC()))
						errorList.add("NRIC is duplicate in input file~" + readCount + "~" + model.getNRIC() + nric + "~NRIC");
					else {
						nricSet.add(model.getNRIC());
						nricList.add(model.getNRIC() + "~" + readCount);
					}
				}

				//Data type validation
				if(null != model.getAPPL_YEAR()) {
					try {
						NumberUtils.convertBigDecimal(model.getAPPL_YEAR().toString());
					} catch (NumberFormatException e) {
						errorList.add("APPL_YEAR should be a valid year~" + readCount + "~"  + model.getAPPL_YEAR() + nric + "~APPL_YEAR");
					}
				}
				if(null != model.getNTI()) {
					try {
						NumberUtils.convertBigDecimal(model.getNTI().toString());
					} catch (NumberFormatException e) {
						errorList.add("NTI should be a valid number~" + readCount + "~"  + model.getNTI() + nric + "~NTI");
					}
				}
				if(StringUtils.isValid(model.getAPPL_MA_CON_RATE())) {
					try {
						NumberUtils.convertBigDecimal(model.getAPPL_MA_CON_RATE());
					} catch (NumberFormatException e) {
						errorList.add("APPL_MA_CON_RATE should be a valid number~" + readCount + "~"  + model.getAPPL_MA_CON_RATE() + nric + "~APPL_MA_CON_RATE");
					}
				}
				if(StringUtils.isValid(model.getMAX_APPL_MA_CON_RATE())) {
					try {
						NumberUtils.convertBigDecimal(model.getMAX_APPL_MA_CON_RATE());
					} catch (NumberFormatException e) {
						errorList.add("MAX_APPL_MA_CON_RATE should be a valid number~" + readCount + "~"  + model.getMAX_APPL_MA_CON_RATE() + nric + "~MAX_APPL_MA_CON_RATE");
					}
				}
				if(StringUtils.isValid(model.getMAX_APPL_MA_CON_AMOUNT())) {
					try {
						NumberUtils.convertBigDecimal(model.getMAX_APPL_MA_CON_AMOUNT());
					} catch (NumberFormatException e) {
						errorList.add("MAX_APPL_MA_CON_AMOUNT should be a valid number~" + readCount + "~"  + model.getMAX_APPL_MA_CON_AMOUNT() + nric + "~MAX_APPL_MA_CON_AMOUNT");
					}
				}
			} else
				errorList.add("BLANK_LINE found~" + readCount + "~blank~null~DETAIL~BLANK_LINE");
		}

		if(IBIS_D_R_SEP_MA_Rate_Field.lastLineCount.equals(readCount)) {
			logger.info("value of IBIS_D_R_SEP_MA_Rate_Field.lastLineCount - " + IBIS_D_R_SEP_MA_Rate_Field.lastLineCount);

			logger.info("MA Rate NRIC comparison with SEP IRAS NTI NRIC done at the end...");
			//MA Rate NRIC comparison with SEP IRAS NTI NRIC
			if(nricList.size() != irasNRICList.size())
				errorList.add("NRIC_COUNT present in the SEP MA RATE file is not equal to the NRIC count present in the SEP IRAS NTI file~~~null~DETAIL~NRIC_COUNT");

			//if IRAS NRIC has less num of NRICs than the NRICs present in SEP MA Rate
			comparisonList = nricList.stream().filter(x -> !irasNRICList.contains(x.split("~")[0])).collect(Collectors.toList());
			comparisonList.forEach(x -> errorList.add("New NRIC received from SEM~" + x.split("~")[1] + "~" + x.split("~")[0] + "~" + x.split("~")[0] + "~DETAIL~NRIC"));

			//if IRAS NRIC has more num of NRICs than the NRICs present in SEP MA Rate
			comparisonList = new ArrayList<>();
			//remove readcount from nricList
			nricList = nricList.stream().map(x -> x = x.split("~")[0]).collect(Collectors.toList());
			//filter out NRICs which are not matching between the 2 lists
			comparisonList = irasNRICList.stream().filter(x -> !nricList.contains(x)).collect(Collectors.toList());
			comparisonList.forEach(x -> errorList.add("NRIC is not received from SEM~" + "~" + x+ "~" + x + "~DETAIL~NRIC"));

			//numOfRecords - will contain the total read count loaded in Field
			//check footer present in last line
			if(isNull(model)) {
				errorList.add("FOOTER is not present at the end of the file~" + readCount + "~null~null~FOOTER~FOOTER");
				IBIS_D_R_SEP_MA_Rate_Validation_Processor.errorList.addAll(errorList);
				return model;
			} else {
				if(!StringUtils.isValid(model.getRECORD_TYPE()) || (StringUtils.isValid(model.getRECORD_TYPE()) && !model.getRECORD_TYPE().equals("9"))) {
					errorList.add("FOOTER not found~" + readCount + "~" + model.getRECORD_TYPE() + "~null~FOOTER~FOOTER");
					IBIS_D_R_SEP_MA_Rate_Validation_Processor.errorList.addAll(errorList);
					return model;
				}

				try {
					if(!StringUtils.isValid(model.getFooter().substring(1, 9)))
						errorList.add("TOTAL_RECORD_COUNT in FOOTER is empty~" + readCount + "~null~null~FOOTER~TOTAL_RECORD_COUNT");
					recCountF = model.getFooter().substring(1, 9).trim();
					logger.info("TOTAL_RECORD_COUNT value - " + recCountF);
				} catch (Exception e) {
					errorList.add("TOTAL_RECORD_COUNT in FOOTER is empty~" + readCount + "~null~null~FOOTER~TOTAL_RECORD_COUNT");
				}

				if(StringUtils.isValid(recCountF)) {
					try {
						NumberUtils.convertBigDecimal(recCountF);

						if(readCount - 2 != Long.parseLong(recCountF))
							errorList.add("TOTAL_RECORD_COUNT in FOOTER did not match with total number of detail records~" + readCount + "~" + recCountF + "~null~FOOTER~TOTAL_RECORD_COUNT");												
					} catch (NumberFormatException e) {
						errorList.add("TOTAL_RECORD_COUNT in FOOTER should be a valid number~" + readCount + "~" + recCountF + "~null~FOOTER~TOTAL_RECORD_COUNT");
					}										
				}
			}
			logger.info("value of readCount including header & footer - " + readCount);
		}

		IBIS_D_R_SEP_MA_Rate_Validation_Processor.errorList.addAll(errorList);
		logger.info("*****IBIS_D_R_SEP_MA_Rate_Validation_Processor ended*****");
		return model;
	}

	public static boolean isNull(IBIS_D_R_SEP_MA_Rate_Mdl obj) {
		try {
			return Stream.of(obj.getRECORD_TYPE(), obj.getNRIC(), obj.getAPPL_YEAR(), obj.getNTI(), obj.getAPPL_MA_CON_RATE(), obj.getMAX_APPL_MA_CON_RATE(), obj.getMAX_APPL_MA_CON_AMOUNT(), obj.getHeader(), obj.getFooter())
					.allMatch(Objects::isNull);
		} catch (Exception e) {
			String recType = readCount == 1 ? "HEADER" : (IBIS_D_R_SEP_MA_Rate_Field.lastLineCount.equals(readCount) ? "FOOTER" : "DETAIL");
			IBIS_D_R_SEP_MA_Rate_Validation_Processor.errorList.add("Record is not valid~"+ readCount + "~null~null~" + recType + "~Record");
			return true;
		}
	}
}
