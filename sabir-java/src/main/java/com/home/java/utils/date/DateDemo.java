package com.home.java.utils.date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZonedDateTime;

public class DateDemo {

	public static void main(String[] args) {
		// Get the current date and time
	      LocalDateTime currentTime = LocalDateTime.now();
	      System.out.println("Current DateTime: " + currentTime);

	      LocalDate date1 = currentTime.toLocalDate();
	      System.out.println("date1: " + date1);

	      Month month = currentTime.getMonth();
	      int day = currentTime.getDayOfMonth();
	      int seconds = currentTime.getSecond();

	      System.out.println("Month: " + month +", day: " + day +", seconds: " + seconds);

	      ZonedDateTime date2 = ZonedDateTime.parse("2007-12-03T10:15:30+05:30[Asia/Karachi]");
	      System.out.println("date2: " + date2);

	}

}
