package com.home.java.utils.xml;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;


public class XMLUtil {
	public static String encodeSplChars4XML(String aText){
		return encodeSplChars4XML(aText, true);
	}
	
	public static String encodeSplChars4XML(String aText, boolean trim){
		if(aText == null || aText.equals("")) {
			return "";
		}
		
	    final StringBuilder result = new StringBuilder();
	    final StringCharacterIterator iterator = new StringCharacterIterator(aText);
	    char character =  iterator.current();
	    while (character != CharacterIterator.DONE ){
	      if (character == '<') {
	        result.append("&lt;");
	      } else if (character == '>') {
	        result.append("&gt;");
	      } else if (character == '\"') {
	        result.append("&quot;");
	      } else if (character == '\'') {
	        result.append("&#039;");
	      } else if (character == '&') {
	         result.append("&amp;");
	      } else if ((int)character == 160) {
	         result.append(" ");
	      } else {
	        //the char is not a special one,add it to the result as is
	        result.append(character);
	      }
	      character = iterator.next();
	    }
	    
	    if(trim) {
	    	return result.toString().trim();
	    } else {
	    	return result.toString();
	    }
	    
	  }
	
	public static String escapeXMLEntities(String xmlElementValue){
		
		if(StringUtils.isBlank(xmlElementValue)){
			return xmlElementValue;
		}
		
		// To handle partially escaped characters, first do unescape and then escape
		xmlElementValue = StringEscapeUtils.unescapeXml(xmlElementValue);
		xmlElementValue = StringEscapeUtils.escapeXml(xmlElementValue);
		
		return xmlElementValue;
		
	}
}
