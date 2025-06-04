package com.home.java.utils.object;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;



public class StringUtil {
	
	public static String[] TRANSPORT_MODES = new String[] {null, "SEA", "RAIL", "ROAD", "AIR", "MAIL", "NOT IN USE", "PIPELINE"};

	public static String commaSeparateTransportModes(String[] array) {
		if (array != null && array.length > 0) {
			StringBuilder sb = new StringBuilder(); 
			for (int i = 0; i < array.length; i++) {
				if(array[i] == null || "null".equals(array[i])){
					continue;
				}
				String val = TRANSPORT_MODES[Integer.parseInt(array[i])];
				if (i > 0) {
					sb.append(",").append(val);
				}
				else {
					sb.append(val);
				}
				
			}			
			return sb.toString();
		}
		return null;
	}
	
	public static String toUpperCase(String inString) {
		if(inString == null)
			return null;
		return inString.toUpperCase().trim();
	}
	
	public static String toCamelCase(String inString) {
		 return inString.substring(0, 1).toUpperCase() +  inString.substring(1); 
	} 

	
	public static String commaSeparate(String[] array) {
		if (array == null || array.length == 0 ) {
			return null;
		}
		StringBuilder sb = new StringBuilder(); 
		for (int i = 0; i < array.length; i++) {
			if(array[i] == null || "null".equals(array[i]) || "".equals(array[i])){
				continue;
			}
			if (i > 0) {
				sb.append(",").append(array[i]);
			}
			else {
				sb.append(array[i]);
			}
			
		}	
		if(sb.toString().equals("")) {
			return null;
		}
		return sb.toString();
	}
	
	public static String concatString(String[] array, String delimi, int maxSize) {
		if (array == null || array.length == 0 ) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		int index = 0;
		for (String aStr : array) {
			if(aStr == null || "null".equals(aStr) || "".equals(aStr.trim())){
				continue;
			}
			if (index > 0) {
				sb.append(delimi).append(aStr);
			} else {
				sb.append(aStr);
			}
			index++;
		}	
		String tmp = sb.toString().trim();
		if(tmp == null || tmp.equals("")) {
			return null;
		}
		if(maxSize >= 1) {
			int len = tmp.length();
			if(len > maxSize) {
				return tmp.substring(0, maxSize);
			} else {
				return tmp;
			}
		} else {
			return tmp;
		}
	}
	
	public static String commaSeparate(Long[] array) { 
		if (array == null || array.length == 0) {
			return null;
		}
		StringBuilder sb = new StringBuilder(); 
		for (int i = 0; i < array.length; i++) {
			if(array[i] == null){
				continue;
			}
			if (i > 0) {
				sb.append(",").append(array[i]);
			}
			else {
				sb.append(array[i]);
			}				
		}			
		if(sb.toString().equals("")) {
			return null;
		}
		return sb.toString();
	}
	
	public static String commaSeparateWithSpace(List<String> arrayList) { 
		if(arrayList == null || arrayList.isEmpty()) {
			return null;
		}
		StringBuilder sb = new StringBuilder(""); 
		int i = 0;
		for(String aStr : arrayList) {
			i++;
			if(i == 1) {
				sb.append(aStr);
			} else {
				sb.append(", ").append(aStr);
			}
		}
		return sb.toString();
	}
	
	/**
	  * 
	  * @param arlList
	  * remove duplicates and maintains same order 
	  */
	 public static void removeDuplicateWithOrder(ArrayList<String> arlList) {
		 if(arlList == null || arlList.size() <= 1) {
			 return ;
		 }
		 Set<String> set = new HashSet<String>();
		 List<String> newList = new ArrayList<String>();
		 for (Iterator<String> iter = arlList.iterator();    iter.hasNext(); ) {
		      String element = iter.next();
		      if (set.add(element)) {
		        newList.add(element);
		      }
		 }
		 arlList.clear();
		 arlList.addAll(newList);
	 } 
	 
	
	public static String[] covertToStringArray(String commaSeperatedStr){
		String array[] = null;
		if(commaSeperatedStr != null){
			array = commaSeperatedStr.split(",");
		}
		return array;		
	}
	
	public static Long[] covertToLongArray(String commaSeperatedStr){
		Long longArr[] = null;
		if(commaSeperatedStr != null){
			String array[] = commaSeperatedStr.split(",");
			longArr = new Long[array.length];
			for (int i = 0; i < array.length; i++) {
				if(array[i] == null || "null".equals(array[i])){
					continue;
				}
				longArr[i] = new Long(array[i]);
			}
		}
		return longArr;		
	}
	public static String asJavascriptArray(String[] array) {

		if (array != null && array.length > 0) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < array.length; i++) {
				if(array[i] == null || "null".equals(array[i])){
					continue;
				}
				if (i > 0) {
					sb.append("','").append(array[i]);
				} else {
					sb.append(array[i]);
				}				
			}
			sb.insert(0, "['").append("']");
			return sb.toString();
		}
		return "[]";
	}
	
	public static String asJavascriptArray(Long[] array) {

		if (array != null && array.length > 0) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < array.length; i++) {
				if(array[i] == null || "null".equals(array[i])){
					continue;
				}
				if (i > 0) {
					sb.append("','").append(array[i]);
				} else {
					sb.append(array[i]);
				}				
			}
			sb.insert(0, "['").append("']");
			return sb.toString();
		}
		return "[]";
	}

	public static String allowAlphaNumericOnly(String aText){
		return ignoreSpecialCharacters(aText,"m");
	}
	
	public static String allowAlphaNumericAndOptionalCharsOnly(String aText){
		return ignoreSpecialCharacters(aText,"mo");
	}
	
	public static String allowAlphaNumericAndSecialChars(String aText){
		return ignoreSpecialCharacters(aText,"t");
	}
	
	
	public static String ignoreSpecialCharacters(String aText, String type){
		if(aText == null || aText.equals("")) {
			return "";
		}		
	    final StringBuilder result = new StringBuilder();
	    final StringCharacterIterator iterator = new StringCharacterIterator(aText);
	    char ch =  iterator.current();
	    while (ch != CharacterIterator.DONE ){
	      int ascii = (int)ch;
	      
	      if("t".equals(type)){//Free Text
		      if( (ascii >= 65 && ascii <= 90) || (ascii >= 97 && ascii <= 122)
		    		 || (ascii >= 48 && ascii <= 57) || ascii == 45 || ascii == 46 || ascii == 32) {
		    		 result.append(ch);		    	  
		      } 
	      }else if("m".equals(type)){//alpha numeric
	    	  if( (ascii >= 65 && ascii <= 90) || (ascii >= 97 && ascii <= 122)
			    		 || (ascii >= 48 && ascii <= 57)) {
			    	 result.append(ch);		    	  
			   } 
	      }else if("mo".equals(type)){//alpha numeric
	    	  if( (ascii >= 65 && ascii <= 90) || (ascii >= 97 && ascii <= 122)
			    		 || (ascii >= 48 && ascii <= 57) || ascii == 40 || ascii == 41) {
			    	 result.append(ch);		    	  
			   } 
	      }
	      
	      ch = iterator.next();
	    }
	    return result.toString().trim();
	 }	
	  
	 public static String[] splitByNoOfChars(String str, int noOfChars){
		ArrayList<String> resultList = new ArrayList<String>();
		splitByNoOfChars(str,noOfChars,resultList);
		return (String[])resultList.toArray(new String[resultList.size()]);
	}
		
	private static void splitByNoOfChars(String str, int noOfChars,ArrayList<String> resultList) {
		if (str != null) {
			if (str.length() > noOfChars) {
				resultList.add(str.substring(0, noOfChars));
				str = str.substring(noOfChars);
				if (str.length() > noOfChars) {
					splitByNoOfChars(str, noOfChars, resultList);
				} else {
					resultList.add(str);
				}
			} else {
				resultList.add(str);
			}
		}
	}
	
	public static String formatStringWithReturnKey(String inString, int noOfLines, int lineSize) {
		return formatStringWithReturnKey(inString, noOfLines, lineSize, true);
	}
	
	public static String formatStringWithReturnKey(String inString, int noOfLines, int lineSize, boolean trim) {
		if (inString == null || inString.trim().length() == 0) {
			return null;
		}
		
		if(trim) {
			inString = StringUtil.toUpperCase(inString.trim());
		} else {
			inString = StringUtil.toUpperCase(inString);
		}
					
		inString = inString.replaceAll("\\r\\n", "\n");
		String[] arr = inString.split("\n");
		StringBuffer resultStringBuf = new StringBuffer("");
		int lineCount = 0;
		for (String a : arr) {
			if (a != null && !a.trim().equals("")) {
				if (a.trim().length() <= lineSize) {
					lineCount++;
					if (lineCount > 1) {
						resultStringBuf.append("\n");
					}
					if(trim) {
						resultStringBuf.append(a.trim());
					} else {
						resultStringBuf.append(a);
					}
				} else {
					String splitStr[] = splitByNoOfChars(a, lineSize);
					for (int i = 0; i < splitStr.length; i++) {
						lineCount++;
						if (i > 0 || lineCount > 1) {
							resultStringBuf.append("\n");
						}
						resultStringBuf.append(splitStr[i]);
						if (lineCount == noOfLines) {
							break;
						}
					}
				}
				if (lineCount == noOfLines) {
					break;
				}

			}
		}
		return resultStringBuf.toString();
	}
	
	public static ArrayList<String> formatTextWithWordwrapToListNew(String aText, int lineSize) {
		if(aText == null || aText.equals("")) {
			return new ArrayList<String>();
		}
		ArrayList<String> lineArr = new ArrayList<String>();
		//to ignore more empty lines
		aText = aText.replaceAll("\r\n", "\n");
		aText = aText.replaceAll("\r", "\n");
		aText = aText.replaceAll("\n\r", "\n");
		StringBuilder result = new StringBuilder();
	    final StringCharacterIterator iterator = new StringCharacterIterator(aText);
	    char character =  iterator.current();
	    int currLineCharIndex = -1;
	    int lineCount = 0;
	    while (character != CharacterIterator.DONE ){
	    	currLineCharIndex++;
		    if(character == '\n') {
		    	if(result.length() == 0) { //for double enter line
					currLineCharIndex = -1;
	    			result = new StringBuilder("");
				} else if(result.length() == 1 &&  result.charAt(0) == '\n') { //skip if its only empty line
				   currLineCharIndex = -1;
	    		   result = new StringBuilder("");	
				} else {
			    	lineArr.add(result.toString());
			    	lineCount++;
			    	currLineCharIndex = -1;
	    		    result = new StringBuilder("");
			    }
	        } else if(currLineCharIndex+1 <= lineSize) {
	    	  result.append(character);
	    	} else if(character == ' ') {
	    		lineArr.add(result.toString());
	    		currLineCharIndex = -1;
	    		lineCount++;
	    		result = new StringBuilder("");
	    	} else {
	    		String tmp = result.toString();
	    		int spaceIndex = tmp.lastIndexOf(' ');
	    		if(spaceIndex == -1 ) {
		    		iterator.setIndex(iterator.getIndex() - 1);
		    		lineArr.add(result.substring(0, tmp.length()));
		    		currLineCharIndex = -1;
		    		lineCount++;
		    		result = new StringBuilder("");
	    		} else	{
		    		int diff = tmp.length() - spaceIndex;
		    		iterator.setIndex(iterator.getIndex() - diff);
		    		String temp1 = result.substring(0, spaceIndex);
		    		if(temp1.trim().length() >=1) {
		    			lineArr.add(temp1);
		    			lineCount++;
		    		}
		    		currLineCharIndex = -1;
		    		result = new StringBuilder("");
	    		} 
	    		
	    	}
	        character = iterator.next();
	    }
	    //add the last line
	   	if(result.toString().length() > 0) {
	   		lineArr.add(result.toString());
	    }
	    return lineArr ;
	}
	
	public static ArrayList<String> formatTextWithWordwrapToList(String aText, int lineSize) {
		if(aText == null || aText.equals("")) {
			return new ArrayList<String>();
		}
		ArrayList<String> lineArr = new ArrayList<String>();
		//to ignore more empty lines
		aText = aText.replaceAll("\\r\\n", "\n");
		aText = aText.replaceAll("\\r", "\n");
		aText = aText.replaceAll("\\n\\r", "\n");
		StringBuilder result = new StringBuilder();
	    final StringCharacterIterator iterator = new StringCharacterIterator(aText);
	    char character =  iterator.current();
	    int currLineCharIndex = -1;
	    int lineCount = 0;
	    while (character != CharacterIterator.DONE ){
	    	currLineCharIndex++;
		    if(character == '\n') {
		    	if(!result.toString().equals("\\n")) { //skip if its only empty line
		    		lineArr.add(result.toString());
		    		lineCount++;
		    	}
	    		currLineCharIndex = -1;
	    		
	    		result = new StringBuilder("");
	        } else if(currLineCharIndex+1 <= lineSize) {
	    	  result.append(character);
	    	} else if(character == ' ') {
	    		lineArr.add(result.toString());
	    		currLineCharIndex = -1;
	    		lineCount++;
	    		result = new StringBuilder("");
	    	} else {
	    		String tmp = result.toString();
	    		int spaceIndex = tmp.lastIndexOf(' ');
	    		if(spaceIndex == -1 ) {
		    		iterator.setIndex(iterator.getIndex() - 1);
		    		lineArr.add(result.substring(0, tmp.length()));
		    		currLineCharIndex = -1;
		    		lineCount++;
		    		result = new StringBuilder("");
	    		} else	{
		    		int diff = tmp.length() - spaceIndex;
		    		iterator.setIndex(iterator.getIndex() - diff);
		    		String temp1 = result.substring(0, spaceIndex);
		    		if(temp1.trim().length() >=1) {
		    			lineArr.add(temp1);
		    			lineCount++;
		    		}
		    		currLineCharIndex = -1;
		    		result = new StringBuilder("");
	    		} 
	    		
	    	}
	        character = iterator.next();
	    }
	    //add the last line
	   	if(result.toString().length() > 0) {
	   		lineArr.add(result.toString());
	    }
	    return lineArr ;
	}
	
	public static String commaSeparateForInClause(String[] array) {
		if (array == null || array.length == 0 ) {
			return null;
		}
		StringBuilder sb = new StringBuilder(); 
		for (int i = 0; i < array.length; i++) {
			if(array[i] == null || "null".equals(array[i]) || "".equals(array[i])){
				continue;
			}
			if (i > 0) {
				sb.append("','").append(array[i]);
			}
			else {
				sb.append(array[i]);
			}
			
		}	
		if(sb.toString().equals("")) {
			return null;
		}		
		return sb.toString();
	}
	
	
	public static boolean hasText(String str) {
		return str != null && str.trim().length() > 0;
	}
	
	public static String convert2Csv(HashMap<String, String> hMap) {
		if (hMap == null || hMap.size()==0) 
			return null;
		
		StringBuffer sb = new StringBuffer();
		
		Iterator<String> it = hMap.keySet().iterator();
		
		while(it.hasNext()){
			sb.append(it.next());
			sb.append(",");
		}
		String st = sb.toString();
		return st.substring(0, st.length()-1);
	}
	
	
	
	
}

