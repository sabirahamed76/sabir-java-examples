package com.home.java.utils.validate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtil {

	public static Pattern USERNAME_PATTERN = Pattern.compile("^[a-z0-9_-]{3,15}$");
	  
	public static Pattern WHITELIST_TAGS_PATTERN = Pattern
			.compile("(?i)<((?![\\s\\r\\n\\f\\t\\e/]*(b|i|u|s|table|tr|td|th|h1|h2|h3|h4|h5|h6|thead|tbody|strike|a|br|p|font|ol|ul|li|span|sup|sub|em|strong|img)[\\s\\r\\n\\f\\t\\e>]+)|([^>]+[\\s\\r\\n\\f\\t\\e]+(on|href)))[^>]*?>");

	public static Pattern NO_TAGS_PATTERN = Pattern
			.compile("<[\\s\\r\\n\\f\\t\\e/]*(.)*[\\s\\r\\n\\f\\t\\e/]*>");

	public static Pattern BOOLEAN_PATTERN = Pattern
			.compile("(?i)^[\\s]*true|false[\\s]*$");

	public static Pattern NUMERIC_PATTERN = Pattern
			.compile("^[\\s]*[0-9]*[\\s]*$");

	public static Pattern DECIMAL_PATTERN = Pattern
			.compile("^[\\s]*[0-9]*\\.?[0-9]*[\\s]*$");

	public static Pattern ALPHA_NUMERIC_PATTERN = Pattern
			.compile("^[\\s]*[0-9a-zA-Z]*[\\s]*$");

	public static boolean isValidString(String str) {
		Matcher matcher = NO_TAGS_PATTERN.matcher(str);
		return !matcher.find();
	}
	
	private ValidationUtil() {
		
	}
}
