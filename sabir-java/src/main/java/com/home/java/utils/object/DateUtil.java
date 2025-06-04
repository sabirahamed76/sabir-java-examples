package com.home.java.utils.object;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class DateUtil {

	private static List<SimpleDateFormat>	
	    dateFormats = new ArrayList<SimpleDateFormat>() {{
	    add(new SimpleDateFormat("M/dd/yyyy"));
	    add(new SimpleDateFormat("dd.M.yyyy"));
	    add(new SimpleDateFormat("M/dd/yyyy hh:mm:ss a"));
	    add(new SimpleDateFormat("dd.M.yyyy hh:mm:ss a"));
	    add(new SimpleDateFormat("dd.MMM.yyyy"));
	    add(new SimpleDateFormat("dd-MMM-yyyy"));
	}
	};
	
	public static SimpleDateFormat getDateFormat(String input) {
	    Date date = null;
	    if(input == null) {
	        return null;
	    }
	    for (SimpleDateFormat format : dateFormats) {
	        try {
	            format.setLenient(false);
	            date = format.parse(input);
	        } catch (ParseException e) {
	        }
	        if (date != null) {
	        	return format;
	        }
	    }

	    return null;
	}
	
	 public static Date convertToDate(String input) {
        Date date = null;
        if(input == null) {
            return null;
        }
        for (SimpleDateFormat format : dateFormats) {
            try {
                format.setLenient(false);
                date = format.parse(input);
            } catch (ParseException e) {
               	System.out.println("Wrong Date format");
            }
            if (date != null) {
                break;
            }
        }
 
        return date;
    }

	public static int daysBetween(Date startDate, Date endDate) {
		if (startDate == null || endDate == null) {
			return 0;
		}
		return (int)( (endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24));
	}
	
	public static Date parseDate(String dateString, String format){
		if (dateString == null)
			return null;
		
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);						
		try {			
			return dateFormat.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Date();
		
	}
	
	public static boolean isValidEtd(String etd) {
		etd = etd.trim();
		if(etd == null || etd.length() != 4) {
			return false;
		}
		boolean result = true;
		try {
			new Integer(etd.substring(0, 2));
			new Integer(etd.substring(2, 4));
			if(new Integer(etd.substring(0, 2)) >= 24) {
				result = false;
			} else if(new Integer(etd.substring(2, 4)) >= 60) {
				result = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}	
		return result;
	}
	
	public static String formatDate(Date dateObj, String format){
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);						
		try {
			return dateFormat.format(dateObj);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return "";
	}
	
	
	public static Date[] getDateIntervalsForExchangeRate(){	
		Calendar startDate = GregorianCalendar.getInstance();
		startDate.set(Calendar.HOUR, 0);
		startDate.set(Calendar.MINUTE, 0);
		startDate.set(Calendar.SECOND, 0);
		startDate.set(Calendar.MILLISECOND, 0);
		int dayOfWeek = startDate.get(Calendar.DAY_OF_WEEK);
        while (dayOfWeek  != Calendar.TUESDAY) {
	        startDate.add(Calendar.DATE, -1);
	        dayOfWeek = startDate.get(Calendar.DAY_OF_WEEK);
	    }
        
        Calendar endDate = GregorianCalendar.getInstance();
        endDate.setTime(startDate.getTime());
        endDate.add(Calendar.DATE, 6);        
        endDate.set(Calendar.HOUR, 23);
        endDate.set(Calendar.MINUTE, 59);
        endDate.set(Calendar.SECOND, 59);
        endDate.set(Calendar.MILLISECOND, 0);
		
        return new Date[] {startDate.getTime(), endDate.getTime()}; 
	}
	
	public static boolean isValidExchangeRateInterval(Date fromDate, Date toDate){	
		if(fromDate == null || toDate == null){
			return false;
		}
		
		/*Calendar today = GregorianCalendar.getInstance();
		today.set(Calendar.HOUR, 0);
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.SECOND, 0);
		today.set(Calendar.MILLISECOND, 0);
		
		if(fromDate.before(today.getTime())){
			return false;
		}*/
		
		Calendar fromCal = GregorianCalendar.getInstance();
		fromCal.setTime(fromDate);
		fromCal.set(Calendar.HOUR, 0);
		fromCal.set(Calendar.MINUTE, 0);
		fromCal.set(Calendar.SECOND, 0);
		fromCal.set(Calendar.MILLISECOND, 0);		
		int fromDayOfWeek = fromCal.get(Calendar.DAY_OF_WEEK);
		
		if(fromDayOfWeek != Calendar.TUESDAY){
			return false;
		}
		
		Calendar toCal = GregorianCalendar.getInstance();
		toCal.setTime(toDate);
		toCal.set(Calendar.HOUR, 0);
		toCal.set(Calendar.MINUTE, 0);
		toCal.set(Calendar.SECOND, 0);
		toCal.set(Calendar.MILLISECOND, 0);		
		int toDayOfWeek = toCal.get(Calendar.DAY_OF_WEEK);
		
		if(toDayOfWeek != Calendar.MONDAY){
			return false;
		}
		
		long d1 = fromCal.getTime().getTime(); 
	    long d2 = toCal.getTime().getTime(); 
	      
		long difMil = d2-d1; 
		long milPerDay = 1000*60*60*24; 

	    long days = difMil / milPerDay; 

	    if(days == 6){
	    	return true;
	    }else{
	    	return false;
	    }
	}
	
		
	public static int getDayOfWeek(){
		Calendar calendar = Calendar.getInstance(); 
		int weekday = calendar.get(Calendar.DAY_OF_WEEK); 		     
		return weekday; 
	}
		
}
