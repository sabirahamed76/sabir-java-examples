/*
 * DateUtility.java
 *
 * Created on October 15, 2008, 10:52 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.home.java.utils.date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class DateUtility {
    
	public static void displayDatesUptoToday() {
		ArrayList dateList=new ArrayList();
		Date d1=new Date();
		Calendar c1 = Calendar.getInstance();
        c1.set(Calendar.DATE, 25);
        c1.set(Calendar.MONTH, 8);
        c1.set(Calendar.YEAR, 2012);
		Calendar c2 = Calendar.getInstance();
		c2.setTime(d1);
		System.out.println("-------------------------------------");
		int i=1;
		dateList.add(c1.getTime());
		while (c1.before(c2)){
			c1.add(Calendar.DAY_OF_MONTH, 1);
			dateList.add(c1.getTime());
			System.out.println(i+"----"+c1.getTime());
			i++;
		}
		
		System.out.println("-------------------------------------"+dateList.size());
	}
	
	
    static void displayDateInfo(GregorianCalendar cal){
		String days[] = {"","Sun","Mon","Tue","Wed","Thu","Fri", "Sat"};
		String months[] = {"January","February","March","April", "May",
		"June","July","August","September","October","November", "December"};
		String am_pm[] = {"AM","PM"};
		System.out.println("Year: "+cal.get(Calendar.YEAR));
		System.out.println("Month: "+months[cal.get(Calendar. MONTH)]);
		System.out.println("Date: "+cal.get(Calendar.DATE));
		System.out.println("Day: "+days[cal.get(Calendar.DAY_OF_WEEK)]);
		System.out.println("Hour: "+(cal.get(Calendar.HOUR)+12)%13);
		System.out.println("Minute: "+cal.get(Calendar.MINUTE));
		System.out.println("Second: "+cal.get(Calendar.SECOND));
		System.out.println(am_pm[cal.get(Calendar.AM_PM)]);
		TimeZone tz=cal.getTimeZone();
		System.out.println("Time Zone: "+tz.getID());
	}

	/* Add Day/Month/Year to a Date
	 add() is used to add  values to a Calendar object. 
	 You specify which Calendar field is to be affected by the operation 
	 (Calendar.YEAR, Calendar.MONTH, Calendar.DATE). 
	 */

	public static final String DATE_FORMAT = "dd-MM-yyyy";
	//See Java DOCS for different date formats
	//	public static final String DATE_FORMAT = "yyyy-MM-dd";
	
	public static void addToDate() {
		System.out.println("1. Add to a Date Operation\n");
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		//Gets a calendar using the default time zone and locale.
		Calendar c1 = Calendar.getInstance();
		Date d1 = new Date();
		//		System.out.println("Todays date in Calendar Format : "+c1);
		System.out.println("c1.getTime() : " + c1.getTime());
		System.out.println("c1.get(Calendar.YEAR): "+ c1.get(Calendar.YEAR));
		System.out.println("Todays date in Date Format : " + d1);
		c1.set(1999, 0, 20); //(year,month,date)
		System.out.println("c1.set(1999,0 ,20) : " + c1.getTime());
		c1.add(Calendar.DATE, 20);
		System.out.println("Date + 20 days is : "+ sdf.format(c1.getTime()));
		System.out.println();
		System.out.println("-------------------------------------");
	}
	
	
	/*Substract Day/Month/Year to a Date

	 roll() is used to substract values to a Calendar object. 
	 You specify which Calendar field is to be affected by the operation 
	 (Calendar.YEAR, Calendar.MONTH, Calendar.DATE). 	 

	 Note: To substract, simply use a negative argument. 
	 roll() does the same thing except you specify if you want to roll up (add 1) 
	 or roll down (substract 1) to the specified Calendar field. The operation only
	 affects the specified field while add() adjusts other Calendar fields. 
	 See the following example, roll() makes january rolls to december in the same 
	 year while add() substract the YEAR field for the correct result. Hence add() 
	 is preferred even for subtraction by using a negative element.

	 */

	public static void subToDate() {
	
		System.out.println("2. Subtract to a date Operation\n");
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		Calendar c1 = Calendar.getInstance();
		c1.set(1999, 0, 20);
		System.out.println("Date is : " + sdf.format(c1.getTime()));
		// roll down, substract 1 month
		c1.roll(Calendar.MONTH, false);
		System.out.println("Date roll down 1 month : "+ sdf.format(c1.getTime()));
		c1.set(1999, 0, 20);
		System.out.println("Date is : " + sdf.format(c1.getTime()));
		c1.add(Calendar.MONTH, -1);
		// substract 1 month
		System.out.println("Date minus 1 month : "+ sdf.format(c1.getTime()));
		System.out.println();
		System.out.println("-------------------------------------");
	}
	
	public static void daysBetween2Dates() {
	
		System.out.println("3. No of Days between 2 dates\n");
		Calendar c1 = Calendar.getInstance(); //new GregorianCalendar();
		Calendar c2 = Calendar.getInstance(); //new GregorianCalendar();
		c1.set(1999, 0, 20);
		c2.set(1999, 0, 22);
		System.out.println("Days Between " + c1.getTime() + " and "
				+ c2.getTime() + " is");
		System.out.println((c2.getTime().getTime() - c1.getTime()
				.getTime())	/ (24 * 3600 * 1000));
		System.out.println();
		System.out.println("-------------------------------------");
	}
	
	public static void daysInMonth() {
		
		System.out.println("4. No of Days in a month for a given date\n");
		Calendar c1 = Calendar.getInstance(); // new GregorianCalendar();
		c1.set(1999, 6, 20);
		int year = c1.get(Calendar.YEAR);
		int month = c1.get(Calendar.MONTH);
		// int days = c1.get(Calendar.DATE);
		int[] daysInMonths = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30,31 };
		daysInMonths[1] += DateUtility.isLeapYear(year) ? 1 : 0;
		System.out.println("Days in " + month + "th month for year" + year
				+ "is " + daysInMonths[c1.get(Calendar.MONTH)]);
		System.out.println();
		System.out.println("-------------------------------------");
	}

	public static void validateAGivenDate() {
		
		System.out.println("5. Validate a given date\n");
		String dt = "20011223";
		String invalidDt = "20031315";
		String dateformat = "yyyyMMdd";
		Date dt1 = null, dt2 = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(dateformat);
			sdf.setLenient(false);
			dt1 = sdf.parse(dt);
			dt2 = sdf.parse(invalidDt);
			System.out.println("Date is ok = " + dt1 + "(" + dt + ")");
		} catch (ParseException e) {
			System.out.println(e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println("Invalid date");
		}
		System.out.println();
		System.out.println("-------------------------------------");
	}
	
	public static void compare2Dates() {
		
		System.out.println("6. Comparision of 2 dates\n");
		SimpleDateFormat fm = new SimpleDateFormat("dd-MM-yyyy");
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.set(2000, 02, 15);
		c2.set(2001, 02, 15);
		System.out.print(fm.format(c1.getTime()) + " is ");
		if (c1.before(c2)) {
			System.out.println("less than " + fm.format(c2.getTime()));
		} else if (c1.after(c2)) {
			System.out.println("greater than " + fm.format(c2.getTime()));
		} else if (c1.equals(c2)) {
			System.out.println("is equal to " + fm.format(c2.getTime()));
		}
		System.out.println();
		System.out.println("-------------------------------------");
	}
	
	public static void getDayofTheDate() {
		
		System.out.println("7. Get the day for a given date\n");
		Date d1 = new Date();
		String day = null;
		DateFormat f = new SimpleDateFormat("EEEE");
		try {
			day = f.format(d1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("The day for " + d1 + " is " + day);
		System.out.println();
		System.out.println("-------------------------------------");
	}
	
	//Utility Method to find whether an Year is a Leap year or Not
	public static boolean isLeapYear(int year) {
		
		if ((year % 100 != 0) || (year % 400 == 0)) {
			return true;
		}
		return false;
	}
	
	public static void main(String args[]) {
        /*        Date today = new Date();
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(today);
		System.out.println("Today: ");
		displayDateInfo(cal);
		cal.clear();
		cal.set(2000,0,1);
		System.out.println("\nNew Years Day 2000: ");

		displayDateInfo(cal);
                
		addToDate(); //Add day, month or year to a date field.
		subToDate(); //Subtract day, month or year to a date field.
		daysBetween2Dates();
		//The "right" way would be to compute the Julian day number of 
		//both dates and then do the subtraction.
		daysInMonth();//Find the number of days in a month for a date
		validateAGivenDate();//Check whether the date format is proper
		compare2Dates(); //Compare 2 dates
		getDayofTheDate();
		*/
		displayDatesUptoToday();
	}
	
	/**
     * <p>Checks if two dates are on the same day ignoring time.</p>
     * @param date1  the first date, not altered, not null
     * @param date2  the second date, not altered, not null
     * @return true if they represent the same day
     * @throws IllegalArgumentException if either date is <code>null</code>
     */
    public static boolean isSameDay(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            throw new IllegalArgumentException("The dates must not be null");
        }
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        return isSameDay(cal1, cal2);
    }
    
    /**
     * <p>Checks if two calendars represent the same day ignoring time.</p>
     * @param cal1  the first calendar, not altered, not null
     * @param cal2  the second calendar, not altered, not null
     * @return true if they represent the same day
     * @throws IllegalArgumentException if either calendar is <code>null</code>
     */
    public static boolean isSameDay(Calendar cal1, Calendar cal2) {
        if (cal1 == null || cal2 == null) {
            throw new IllegalArgumentException("The dates must not be null");
        }
        return (cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) &&
                cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR));
    }
    
    /**
     * <p>Checks if a date is today.</p>
     * @param date the date, not altered, not null.
     * @return true if the date is today.
     * @throws IllegalArgumentException if the date is <code>null</code>
     */
    public static boolean isToday(Date date) {
        return isSameDay(date, Calendar.getInstance().getTime());
    }
    
    /**
     * <p>Checks if a calendar date is today.</p>
     * @param cal  the calendar, not altered, not null
     * @return true if cal date is today
     * @throws IllegalArgumentException if the calendar is <code>null</code>
     */
    public static boolean isToday(Calendar cal) {
        return isSameDay(cal, Calendar.getInstance());
    }
    
    /**
     * <p>Checks if the first date is before the second date ignoring time.</p>
     * @param date1 the first date, not altered, not null
     * @param date2 the second date, not altered, not null
     * @return true if the first date day is before the second date day.
     * @throws IllegalArgumentException if the date is <code>null</code>
     */
    public static boolean isBeforeDay(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            throw new IllegalArgumentException("The dates must not be null");
        }
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        return isBeforeDay(cal1, cal2);
    }
    
    /**
     * <p>Checks if the first calendar date is before the second calendar date ignoring time.</p>
     * @param cal1 the first calendar, not altered, not null.
     * @param cal2 the second calendar, not altered, not null.
     * @return true if cal1 date is before cal2 date ignoring time.
     * @throws IllegalArgumentException if either of the calendars are <code>null</code>
     */
    public static boolean isBeforeDay(Calendar cal1, Calendar cal2) {
        if (cal1 == null || cal2 == null) {
            throw new IllegalArgumentException("The dates must not be null");
        }
        if (cal1.get(Calendar.ERA) < cal2.get(Calendar.ERA)) return true;
        if (cal1.get(Calendar.ERA) > cal2.get(Calendar.ERA)) return false;
        if (cal1.get(Calendar.YEAR) < cal2.get(Calendar.YEAR)) return true;
        if (cal1.get(Calendar.YEAR) > cal2.get(Calendar.YEAR)) return false;
        return cal1.get(Calendar.DAY_OF_YEAR) < cal2.get(Calendar.DAY_OF_YEAR);
    }
    
    /**
     * <p>Checks if the first date is after the second date ignoring time.</p>
     * @param date1 the first date, not altered, not null
     * @param date2 the second date, not altered, not null
     * @return true if the first date day is after the second date day.
     * @throws IllegalArgumentException if the date is <code>null</code>
     */
    public static boolean isAfterDay(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            throw new IllegalArgumentException("The dates must not be null");
        }
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        return isAfterDay(cal1, cal2);
    }
    
    /**
     * <p>Checks if the first calendar date is after the second calendar date ignoring time.</p>
     * @param cal1 the first calendar, not altered, not null.
     * @param cal2 the second calendar, not altered, not null.
     * @return true if cal1 date is after cal2 date ignoring time.
     * @throws IllegalArgumentException if either of the calendars are <code>null</code>
     */
    public static boolean isAfterDay(Calendar cal1, Calendar cal2) {
        if (cal1 == null || cal2 == null) {
            throw new IllegalArgumentException("The dates must not be null");
        }
        if (cal1.get(Calendar.ERA) < cal2.get(Calendar.ERA)) return false;
        if (cal1.get(Calendar.ERA) > cal2.get(Calendar.ERA)) return true;
        if (cal1.get(Calendar.YEAR) < cal2.get(Calendar.YEAR)) return false;
        if (cal1.get(Calendar.YEAR) > cal2.get(Calendar.YEAR)) return true;
        return cal1.get(Calendar.DAY_OF_YEAR) > cal2.get(Calendar.DAY_OF_YEAR);
    }
    
    /**
     * <p>Checks if a date is after today and within a number of days in the future.</p>
     * @param date the date to check, not altered, not null.
     * @param days the number of days.
     * @return true if the date day is after today and within days in the future .
     * @throws IllegalArgumentException if the date is <code>null</code>
     */
    public static boolean isWithinDaysFuture(Date date, int days) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return isWithinDaysFuture(cal, days);
    }
    
    /**
     * <p>Checks if a calendar date is after today and within a number of days in the future.</p>
     * @param cal the calendar, not altered, not null
     * @param days the number of days.
     * @return true if the calendar date day is after today and within days in the future .
     * @throws IllegalArgumentException if the calendar is <code>null</code>
     */
    public static boolean isWithinDaysFuture(Calendar cal, int days) {
        if (cal == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar today = Calendar.getInstance();
        Calendar future = Calendar.getInstance();
        future.add(Calendar.DAY_OF_YEAR, days);
        return (isAfterDay(cal, today) && ! isAfterDay(cal, future));
    }
    
    /** Returns the given date with the time set to the start of the day. */
    public static Date getStart(Date date) {
        return clearTime(date);
    }
    
    /** Returns the given date with the time values cleared. */
    public static Date clearTime(Date date) {
        if (date == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }    

    /** Determines whether or not a date has any time values (hour, minute, 
     * seconds or millisecondsReturns the given date with the time values cleared. */

    /**
     * Determines whether or not a date has any time values.
     * @param date The date.
     * @return true iff the date is not null and any of the date's hour, minute,
     * seconds or millisecond values are greater than zero.
     */
    public static boolean hasTime(Date date) {
        if (date == null) {
            return false;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        if (c.get(Calendar.HOUR_OF_DAY) > 0) {
            return true;
        }
        if (c.get(Calendar.MINUTE) > 0) {
            return true;
        }
        if (c.get(Calendar.SECOND) > 0) {
            return true;
        }
        if (c.get(Calendar.MILLISECOND) > 0) {
            return true;
        }
        return false;
    }

    /** Returns the given date with time set to the end of the day */
    public static Date getEnd(Date date) {
        if (date == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.MILLISECOND, 999);
        return c.getTime();
    }

    /** 
     * Returns the maximum of two dates. A null date is treated as being less
     * than any non-null date. 
     */
    public static Date max(Date d1, Date d2) {
        if (d1 == null && d2 == null) return null;
        if (d1 == null) return d2;
        if (d2 == null) return d1;
        return (d1.after(d2)) ? d1 : d2;
    }
    
    /** 
     * Returns the minimum of two dates. A null date is treated as being greater
     * than any non-null date. 
     */
    public static Date min(Date d1, Date d2) {
        if (d1 == null && d2 == null) return null;
        if (d1 == null) return d2;
        if (d2 == null) return d1;
        return (d1.before(d2)) ? d1 : d2;
    }

    /** The maximum date possible. */
    public static Date MAX_DATE = new Date(Long.MAX_VALUE);

} 