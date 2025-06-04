package com.home.java.utils.date;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	private static final SimpleDateFormat displayDateFormatter = new SimpleDateFormat(DateConstants.DATE_FORMAT2);	
	
	public static String toDisplayDate(Date date) {
		if(date != null) {
			return displayDateFormatter.format(date);
		}
		
		return DateConstants.EMPTY;
	}	
}