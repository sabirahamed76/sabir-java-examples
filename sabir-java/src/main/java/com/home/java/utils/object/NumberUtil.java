package com.home.java.utils.object;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class NumberUtil {

	
	public static String formatWholeNumber(Long number, int minimum, int maximum) {
		
		NumberFormat format = new DecimalFormat();
		format.setGroupingUsed(false);
		format.setMinimumFractionDigits(0);
		format.setMinimumIntegerDigits(minimum);
		format.setMaximumIntegerDigits(maximum);
		return format.format(new Double(number).doubleValue());
	}

	public static String formatWholeNumber(int number, int minimum, int maximum) {
		
		NumberFormat format = new DecimalFormat();
		format.setGroupingUsed(false);
		format.setMinimumFractionDigits(0);
		format.setMinimumIntegerDigits(minimum);
		format.setMaximumIntegerDigits(maximum);
		return format.format(new Double(number).doubleValue());
	}
	
	public static String formatNumber(Double number, int decimals) {
	
		if(number == null)
			return "";
		/**
		NumberFormat format = new DecimalFormat();
		//format.getsetRoundingMode(RoundingMode.HALF_UP);
		format.setGroupingUsed(false);
		format.setMinimumFractionDigits(2);
		format.setMaximumFractionDigits(decimals);
		return format.format(number.doubleValue());
		*/
		String format = "%."+decimals+"f";
		return String.format(format, number);
	}
	
	public static String formatNumber6Decimals(Double number) {
		
		if(number == null)
			return "0";
		/**
		NumberFormat format = new DecimalFormat();
		//format.setRoundingMode(RoundingMode.HALF_UP);
		format.setGroupingUsed(false);
		format.setMinimumFractionDigits(2);
		format.setMaximumFractionDigits(6);
		return format.format(number.doubleValue());*/
		
		String format = "%.6f";
		return String.format(format, number);
	}
	public static String formatNumber4Decimals(Double number) {
		
		if(number == null)
			return "0";
		
		/**
		NumberFormat format = new DecimalFormat();
		//format.setRoundingMode(RoundingMode.HALF_UP);
		format.setGroupingUsed(false);
		format.setMinimumFractionDigits(4);
		format.setMaximumFractionDigits(4);
		return format.format(number.doubleValue());
		*/
		String format = "%.4f";
		return String.format(format, number);
	}
	public static String formatNumber3Decimals(Double number) {
		
		if(number == null)
			return "0";
		
		/**
		NumberFormat format = new DecimalFormat();
		//format.setRoundingMode(RoundingMode.HALF_UP);
		format.setGroupingUsed(false);
		format.setMinimumFractionDigits(3);
		format.setMaximumFractionDigits(3);
		return format.format(number.doubleValue());
		*/
		String format = "%.3f";
		return String.format(format, number);
	}
	public static String formatNumber2Decimals(Double number) {
		
		if(number == null)
			return "0";
		
		/**
		NumberFormat format = new DecimalFormat();
		//format.setRoundingMode(RoundingMode.HALF_UP);
		format.setGroupingUsed(false);
		format.setMinimumFractionDigits(2);
		format.setMaximumFractionDigits(2);
		return format.format(number.doubleValue());
		*/
		String format = "%.2f";
		return String.format(format, number);
	}
	

	public static int convertInt(BigInteger biValue){
		if (biValue==null)
			return 0;
		else
			return biValue.intValue();		
	}
	
	public static Double convertDouble(BigInteger biValue){
		if (biValue==null)
			return null;
		else
			return new Double(biValue.intValue());
	}
	
	public static Double convertDouble(BigDecimal bdValue){
		if (bdValue==null)
			return null;
		else
			return new Double(bdValue.doubleValue());
	}
	
	public static Long convertLong(BigInteger biValue){
		if (biValue==null)
			return null;
		else
			return new Long(biValue.intValue());
	}
}
