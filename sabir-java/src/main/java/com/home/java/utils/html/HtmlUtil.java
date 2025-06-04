package com.home.java.utils.html;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;


public class HtmlUtil {
	
	private static transient final Logger logger = Logger.getLogger(HtmlUtil.class);
	
	public static String getHtmlObject(String htmlObjType, String htmlObjName, String htmlObjValue) {
		String res = HtmlConstants.EMPTY;
		if (htmlObjType.equals(HtmlConstants.INPUT_CHECK_BOX)){
			res = HtmlConstants.CHECK_BOX.replaceAll(HtmlConstants.INPUT_CHECK_BOX+HtmlConstants.NAME, htmlObjName);
			res = res.replaceAll(HtmlConstants.INPUT_CHECK_BOX+HtmlConstants.VALUE, htmlObjValue);
		}else if (htmlObjType.equals(HtmlConstants.INPUT_TEXT)){
			res = HtmlConstants.TEXT.replaceAll(HtmlConstants.INPUT_TEXT+HtmlConstants.NAME, htmlObjName);
			res = res.replaceAll(HtmlConstants.INPUT_TEXT+HtmlConstants.VALUE, htmlObjValue);
		}else if (htmlObjType.equals(HtmlConstants.INPUT_PASSWORD)){
			res = HtmlConstants.PASSWORD.replaceAll(HtmlConstants.INPUT_PASSWORD+HtmlConstants.NAME, htmlObjName);
			res = res.replaceAll(HtmlConstants.INPUT_PASSWORD+HtmlConstants.VALUE, htmlObjValue);
		}else if (htmlObjType.equals(HtmlConstants.EDIT_ROW)){
			res = HtmlConstants.EDIT_IMAGE.replaceAll(HtmlConstants.EDIT_ROW + HtmlConstants.NAME, htmlObjName);
			res = res.replaceAll(HtmlConstants.EDIT_ROW+HtmlConstants.VALUE, htmlObjValue);
		}else if (htmlObjType.equals(HtmlConstants.DELETE_ROW)){
			res = HtmlConstants.DELETE_IMAGE.replaceAll(HtmlConstants.DELETE_ROW + HtmlConstants.NAME, htmlObjName);
			res = res.replaceAll(HtmlConstants.DELETE_ROW+HtmlConstants.VALUE, htmlObjValue);
		}
		return res;
	}
	
	
	public static String createHtmlTable(ArrayList<String> hdrList, ArrayList<String> dtlList){
		StringBuffer sb = new StringBuffer();
		sb.append(HtmlConstants.EMPTY)
		  .append(createHtmlTableHead(hdrList))
		  .append(createHtmlTableBody(hdrList,dtlList));
		return sb.toString();
	}
	
	public static String createHtmlTableHead(ArrayList<String> hdrList){
		
		StringBuffer sb = new StringBuffer();
		sb.append(HtmlConstants.EMPTY);		
		
		for (int i=0; i<hdrList.size();i++) {
			if (i==0) {
				sb.append(HtmlConstants.THEAD_START)
					.append(HtmlConstants.TH_START)
					.append(hdrList.get(i))
					.append(HtmlConstants.TH_END);
			} else {
				sb.append(HtmlConstants.TH_START)
					.append(hdrList.get(i))
					.append(HtmlConstants.TH_END);
			}		
			 if (i==hdrList.size()-1) 
				sb.append(HtmlConstants.THEAD_END);
		}
		
		
		return sb.toString();
	}
	
	public static String createHtmlTableBody(ArrayList<String> hdrList,ArrayList<String> dtlList){
		
		StringBuffer sb = new StringBuffer();
		sb.append(HtmlConstants.EMPTY);
		if (dtlList.size()==0)
			return sb.toString();
		
		for (int i=0,j=0; i<dtlList.size();i++) {			
			if (i==0) {
				sb.append(HtmlConstants.TBODY_START)
				  .append(HtmlConstants.TR_START)
				  .append(HtmlConstants.TD_START)
				  .append(formatString((String)dtlList.get(i))) 
				  .append(HtmlConstants.TD_END);
			} else {
				sb.append(HtmlConstants.TD_START)
				  .append(formatString((String)dtlList.get(i)))
				  .append(HtmlConstants.TD_END);
			}
			
			if (j+hdrList.size()-1==i) {
				sb.append(HtmlConstants.TR_END);	
				j = j + hdrList.size();
			}
			if (j-1==i & j-1!=dtlList.size()-1) {
				sb.append(HtmlConstants.TR_START);
			}
			if (i==dtlList.size()-1) 
				sb.append(HtmlConstants.TBODY_END);
		}
		return sb.toString();
	}
	
	public static String formatDate(Date d, String format){
		String result=HtmlConstants.EMPTY;
		try {
			if (d != null){
				SimpleDateFormat sf= new SimpleDateFormat(format);
				result = sf.format(d);	
			}
		}
		catch(Exception e) {			
			logger.error(e.getMessage());
		}
		return result;
	}
	
	public static String formatString(String s){
		String result=HtmlConstants.EMPTY;
		if (StringUtils.isNotBlank(s)){
			result = s.trim();
		}
		return result;		
	}
	
	public static String formatDecimal(Double d, String format){
		String result=HtmlConstants.EMPTY;
		try {
			if (d != null){
				DecimalFormat df = new DecimalFormat(format);
				result = df.format(d);
			}
		}
		catch(Exception e) {
			logger.error(e.getMessage());
		}
		return result;		
	}
}