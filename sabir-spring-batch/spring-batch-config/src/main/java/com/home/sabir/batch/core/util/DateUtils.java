package com.home.sabir.batch.core.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class DateUtils {
	private static final Logger logger = LogManager.getLogger(DateUtils.class);

	public final static String DATE_FORMAT_DD_MMM_YYYY = "dd-MMM-yyyy";
	public final static String DATE_FORMAT_DD_MMM_YY = "dd-MMM-yy";
	public final static String DATE_FORMAT_DD_MM_YYYY = "dd-MM-yyyy";
	public final static String DATE_FORMAT_YYMMDD = "yyMMdd";
	public final static String DATE_FORMAT_YYYYMMDD = "yyyyMMdd";
	public final static String DATE_FORMAT_YYYYMMDD1 = "yyyy-MM-dd";
	public final static String TIMESTAMP_FORMAT_YYYYMMDD = "yyyy-MM-dd HH:mm:ss";
	public final static String DATE_FORMAT_DD_MON_YY = "dd-MON-YY";
	public final static String DATE_FORMAT_YYYYMM = "yyyyMM";
	public final static String TIMESTAMP_FORMAT_DD_MMM_YYYY = "dd-MMM-YY hh.mm.ss.SSSSSSSSS a";
	public final static String YEAR_FORMAT_YYYY = "yyyy";

	public static String holidayDetailSelect = "SELECT TO_CHAR(HDY_DTE,'DD-MON-YY') FROM TB_CPP_HDY WHERE :date <HDY_DTE AND HDY_DTE < ADD_MONTHS(:date, 1) ORDER BY HDY_DTE";

	public static boolean isDateValid(String date, String format) {
		try {
			DateFormat df = new SimpleDateFormat(format);
			df.setLenient(false);
			df.parse(date);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static boolean isHeaderDateValid(String date, String format) {
		try {
			DateTimeFormatter.ofPattern(format).parse(date);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static Date converDate(String date, String sourceFormat, String destFormat) {
		try {
			DateFormat df1 = new SimpleDateFormat(sourceFormat);
			DateFormat df2 = new SimpleDateFormat(destFormat);
			df1.setLenient(false);
			return df2.parse(df2.format(df1.parse(date)));
		} catch (ParseException e) {
			return null;
		}
	}

	public static String formatDate(Date date, String sourceFormat) {
		try {
			DateFormat df1 = new SimpleDateFormat(sourceFormat);
			df1.setLenient(false);
			return df1.format(date);
		} catch (Exception e) {
			return null;
		}
	}

	public static String changeDateFormat(String date, String SourceFormat, String reqFormat) {

		String transformDate = "";
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(SourceFormat);
			Date varDate = dateFormat.parse(date);
			dateFormat = new SimpleDateFormat(reqFormat);
			transformDate = dateFormat.format(varDate);
		} catch (Exception e) {

		}
		return transformDate;
	}

	public static java.sql.Date convertToSqlDate(String date, String sourceFormat) {
		java.sql.Date sqlStartDate = null;
		try {
			SimpleDateFormat df1 = new SimpleDateFormat(sourceFormat);
			df1.setLenient(false);
			java.util.Date utilDate = df1.parse(date);
			sqlStartDate = new java.sql.Date(utilDate.getTime());

		} catch (ParseException e) {
		}
		return sqlStartDate;

	}

	public static java.sql.Date convertInputParamDate(String inputParamDate) {
		java.sql.Date transDate = null;
		if (inputParamDate != null) {
			transDate = convertToSqlDate(inputParamDate, DATE_FORMAT_DD_MMM_YY);
		} else {
			Calendar cal = Calendar.getInstance();
			transDate = new java.sql.Date(cal.getTime().getTime());
		}
		return transDate;
	}

	/*
	 * This method used to get second working day of the given date excluding
	 * the holidays.
	 * 
	 */
	public static String getSecondWorkingDay(String date, NamedParameterJdbcTemplate jdbcTemplate) throws Exception {
		String firstWorkingDay = "", secondWorkingDay = "";

		if (!isDateValid(date, DATE_FORMAT_DD_MMM_YY)) {
			throw new Exception("Date " + date + " is not in valid format " + DATE_FORMAT_DD_MMM_YY);
		}

		try {
			logger.info("Calculating second working day for the date : " + date);

			DateTimeFormatter formatter = new DateTimeFormatterBuilder().parseCaseInsensitive()
					.appendPattern(DATE_FORMAT_DD_MMM_YY).toFormatter(Locale.ENGLISH);
			List<String> monthHolList = null;
			LocalDate startDate = null;
			LocalDate endDate = null;

			MapSqlParameterSource sqlParameters = new MapSqlParameterSource();
			sqlParameters.addValue("date", date);

			while (secondWorkingDay == "") {
				monthHolList = jdbcTemplate.queryForList(holidayDetailSelect, sqlParameters, String.class);
				for (String hol : monthHolList) {
					logger.info("Holiday dates : " + hol);
				}
				for (String hol : monthHolList) {
					startDate = LocalDate.parse(date, formatter);
					endDate = LocalDate.parse(hol, formatter);

					long numOfDaysBetween = ChronoUnit.DAYS.between(startDate, endDate);
					if (numOfDaysBetween < 3) {
						if (numOfDaysBetween == 2) {
							if (firstWorkingDay == "")
								firstWorkingDay = startDate.plusDays(1).format(formatter);
							else
								secondWorkingDay = startDate.plusDays(1).format(formatter);
						}

						date = hol;
						continue;
					} else {
						if (firstWorkingDay == "")
							firstWorkingDay = startDate.plusDays(1).format(formatter);
						else
							secondWorkingDay = startDate.plusDays(1).format(formatter);
						if (secondWorkingDay == "")
							secondWorkingDay = startDate.plusDays(2).format(formatter);

					}
					if (secondWorkingDay != "")
						break;
				}
				if (secondWorkingDay != "")
					break;
			}

			logger.info("Second working day : " + secondWorkingDay);
		} catch (Exception e) {
			logger.error("Error occured in calculating second working day for the given date : ", e);
			throw new Exception(
					"Error occured in calculating second working day for the given date : " + e.getMessage());
		}
		return secondWorkingDay;
	}

	/*
	 * This method used to get next working day of the given date excluding the
	 * holidays.
	 * 
	 */
	public static String getNextWorkingDay(String date, NamedParameterJdbcTemplate jdbcTemplate) throws Exception {
		String nextWorkingDay = "";

		if (!isDateValid(date, DATE_FORMAT_DD_MMM_YY)) {
			throw new Exception("Date " + date + " is not in valid format " + DATE_FORMAT_DD_MMM_YY);
		}

		try {
			logger.info("Calculating next working day for the date : " + date);

			DateTimeFormatter formatter = new DateTimeFormatterBuilder().parseCaseInsensitive()
					.appendPattern(DATE_FORMAT_DD_MMM_YY).toFormatter(Locale.ENGLISH);
			List<String> monthHolList = new ArrayList<String>();
			LocalDate startDate = null;
			LocalDate endDate = null;

			MapSqlParameterSource sqlParameters = new MapSqlParameterSource();
			sqlParameters.addValue("date", date);

			while (nextWorkingDay == "") {
				monthHolList = jdbcTemplate.queryForList(holidayDetailSelect, sqlParameters, String.class);
				for (String hol : monthHolList) {
					logger.info("Holiday dates for next working day : " + hol);
				}
				for (String hol : monthHolList) {
					startDate = LocalDate.parse(date, formatter);
					endDate = LocalDate.parse(hol, formatter);

					long numOfDaysBetween = ChronoUnit.DAYS.between(startDate, endDate);
					if (numOfDaysBetween < 2) {
						date = hol;
						continue;
					} else {
						if (nextWorkingDay == "")
							nextWorkingDay = startDate.plusDays(1).format(formatter);

					}
					if (nextWorkingDay != "")
						break;
				}
				if (nextWorkingDay != "")
					break;
			}
			logger.info("Next working day : " + nextWorkingDay);
		} catch (Exception e) {
			logger.error("Error occured in calculating next working day for the given date : ", e);
			throw new Exception("Error occured in calculating next working day for the given date : " + e.getMessage());
		}
		return nextWorkingDay;
	}
	
	public static String converDateFormat(String date,String sourceFormat,String destFormat) 
	{
	        try {
	            DateFormat df1 = new SimpleDateFormat(sourceFormat);
	            DateFormat df2 = new SimpleDateFormat(destFormat);
	            df1.setLenient(false);
	            return df2.format(df1.parse(date));
	        } catch (ParseException e) {
	            return null;
	        }
	}

}
