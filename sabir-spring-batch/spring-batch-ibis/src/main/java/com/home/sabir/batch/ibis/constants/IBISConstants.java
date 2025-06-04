package com.home.sabir.batch.ibis.constants;

public class IBISConstants {
	
	public static String TYPE_SYS = "SYS";
	public static String TYPE_APP = "APP";
	
	public static String HEADER_RECORD_TYPE = "001";
	public static String FOOTER_RECORD_TYPE = "999";
	public static String DETAILS_RECORD_TYPE = "002";
	public static String DELTA_TYPE_FILE = "D";
	
	public static String COLUMN_HEADER ="HEADER";
	public static String COLUMN_HEADER_MSG ="Invalid HEADER";
	public static String COLUMN_HEADER_NOT ="HEADER not found";
	public static String COLUMN_FOOTER ="FOOTER";
	public static String COLUMN_FOOTER_MSG ="Invalid FOOTER";
	public static String COLUMN_RECORD ="DETAIL";
	public static String COLUMN_RECORD_MSG ="Invalid Detail Row";
	public static String COLUMN_FOOTER_NOT ="FOOTER not found";
	
	public static String INVALID = "Invalid ";	
	
	/*Error Messages*/
	public static String Splitter ="-";
	public static String RECORD_TYPE_COLUMN ="RECORD_TYPE";
	public static String RECORD_TYPE_MSG_HEADER ="HEADER is not present at the start of the file";
	public static String RECORD_TYPE_MSG_FOOTER ="FOOTER is not present at the end of the file";
	public static String RECORD_TYPE_MSG_ROW ="Incorrect Record Type in Detail Row";
	
	public static String RECORD_COUNT_COLUMN ="RECORD_COUNT";
	public static String RECORD_COUNT_MSG ="Incorrect Record count";
	
	public static String ERROR_DESC_COLUMN ="ERROR_DESCRIPTION";
	public static String ERROR_DESC_MSG ="Error description is Empty";
	
	public static String ERROR_CODE_COLUMN ="ERROR_CODE";
	public static String ERROR_CODE_MSG ="Error code is Empty";
	
	public static String DATE_COLUMN ="DATE";
	public static String DATE_FORMAT_MSG ="Invalid File Creation Date";
	
	public static String INVALID_RECORD ="RECORD";
	public static String INVALID_RECORD_MSG ="Invalid Record found in the file";
	
	public static String CODE_VALUE_NRIC_TYPE ="NRIC_TYPE";
	public static String NRIC_COLUMN ="NRIC";
	
	public static String COHORT_TYPE ="FILE_COHORT_TYPE";
	public static String COHORT_TYPE_MSG ="Invalid File cohort Type";
	
	public static final String VAL = "VAL";
	public static final String SPACE =" ";
	
	public static final String SuccessfulRecords ="000";
	public static final String FORCE_RUN = "Y";
	
	public static final String HEADER_ERROR ="Error in Header";
	
	

}
