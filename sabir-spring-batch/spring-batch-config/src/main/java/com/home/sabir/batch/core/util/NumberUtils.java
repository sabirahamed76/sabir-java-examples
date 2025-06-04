package com.home.sabir.batch.core.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.springframework.util.StringUtils;

public class NumberUtils {

	public static String formatNumber(String padType,int length,Double dbl,String format,String fillType) {
		String output="";
		String str="";
		NumberFormat formatter = new DecimalFormat(format);     
		str = formatter.format(dbl)+"";
		for (int i=0;i<length-str.length();i++) {
			output +=fillType;
		}
		if (padType.equals("left")){
			output = output+str;			
		}else if (padType.equals("right")){
			output = str+output;
		}		
		return output;
	}
	
	public static BigDecimal truncateDecimal(double x,int numberofDecimals)
	{
	    if ( x > 0) {
	        return new BigDecimal(String.valueOf(x)).setScale(numberofDecimals, BigDecimal.ROUND_FLOOR);
	    } else {
	        return new BigDecimal(String.valueOf(x)).setScale(numberofDecimals, BigDecimal.ROUND_CEILING);
	    }
	}
	
	public static BigDecimal convertBigDecimal(String obj) throws NumberFormatException {
		String countStr = StringUtils.trimLeadingCharacter(obj, '0');		
		if (countStr.equals(""))
			countStr="0";
		return new BigDecimal(countStr);
	}
	
	public static BigInteger convertBigInteger(String obj) throws NumberFormatException {
		String countStr = StringUtils.trimLeadingCharacter(obj, '0');
		if (countStr.equals(""))
			countStr="0";
		return new BigInteger(countStr);
	}
}
