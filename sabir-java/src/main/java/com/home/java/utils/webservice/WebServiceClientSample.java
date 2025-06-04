package com.home.java.utils.webservice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.home.java.utils.json.JSONUtil;

public class WebServiceClientSample{
	
	public static void main(String[] args) {
		testAnaGroupMemeber();
	}
	
	public String stringWebService(String link) throws Exception {
		return invokeWebService(link).toString();
	}
	
	private JSONObject jsonWebService(String link) throws Exception {
		StringBuffer response = invokeWebService(link);
		JSONObject jsonObj = new JSONObject (response.toString());
		return jsonObj;
	}
	
	private StringBuffer invokeWebService(String link) throws Exception {
		URL url = new URL(link);
		
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		
		return response;
	}
	
	public JSONObject getUserByActivity(String urlLink,JSONObject jsonObject) throws Exception {
		StringBuffer response = new StringBuffer();	       
		JSONObject jsObj = null;
		System.out.println("input------"+jsonObject.toString());
        try {
        	URL url = new URL(urlLink);	   
    		   
        	/*
            //Construnct the Json 
            JSONObject jsonObject = new JSONObject();
   			JSONArray jsArray = new JSONArray(activityNameList);
   			jsonObject.put("groupName", jsArray);*/
   			
   			//Post the Json to Webservice
   			HttpURLConnection con = (HttpURLConnection) url.openConnection();
    		con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setRequestProperty("Content-Type", "application/json");
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);
            OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream());
            out.write(jsonObject.toString());
            out.close();

            //Read the Json from the webervice
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
    		String inputLine;    		  		
    		while ((inputLine = in.readLine()) != null) {
    			response.append(inputLine);
    		}
            in.close();
            
            
        } catch (Exception e) {
        	e.printStackTrace();
        	//logger.debug("\nError while calling REST Service");
        	//logger.debug(e);
        }
        if (StringUtils.isBlank(response.toString())){
        	jsObj = new JSONObject();
        }else {
        	jsObj = new JSONObject (response.toString());
        }
		return jsObj;
	}
	
	private Object preventNull(String name, Object jsonObject) throws JSONException{
		JSONObject jsonObj = (JSONObject) jsonObject;
		if (jsonObj.isNull(name)){
			return "";
		} else{
			return jsonObj.get(name);
		}
	}
	
	
	
	
	
	
	
	private static void testAnaGroupMemeber() {
		WebServiceClientSample ws = new WebServiceClientSample();
		try {
	        JSONObject jsonObject = new JSONObject();
	        ArrayList<String> activityNameList = new ArrayList<String>();
	        //activityNameList.add("PG_STB_LM_TA_MSO");
	        activityNameList.add("PG_STB_LM_TA_AM");
	        //activityNameList.add("PG_STB_LM_TA_AD");
			JSONArray jsArray = new JSONArray(activityNameList);
			jsonObject.put("groupName", jsArray);
			System.out.println("result----"+ws.getUserByActivity("http://172.20.26.31:9090/FrontierAnaWebService/Auth/getGroupMember",jsonObject));
		}catch(Exception e) {
			e.printStackTrace();			
		}
	
	}
	
	private static void testUEN() {
		WebServiceClientSample ws = new WebServiceClientSample();
		String str = "";
		String companyUen = "201400161R";
		HashMap<String, ArrayList> companyDetailMap = new HashMap<String, ArrayList>();
		try {
			
			//Print Value for Key
			JSONObject js = ws.jsonWebService("http://172.20.29.107:8080/FrontierWebService/UEN/INFO/GetUENInfo/201400161R");
			// str = "uenId"; //for String Test
			// str = "acraEntity.address.streetName"; //for Json Object Test
			str = "acraEntity.capitalInfo.currencyCode"; //for Json Array Test
			// str = "acraEntity.capitalInfo.streetName"; //for Wrong Key
			 str = "acraEntity.capitalInfo"; //for Json Object Test

			if (js != null) {
				ArrayList aList = (ArrayList) JSONUtil.getKeyAndValue(str, js);
				if (aList.size()>0) 
					System.out.println("There are "+ aList.size() + " Records found.");
				else
					System.out.println("No Records found.");
				for (int k=0 ; k<aList.size(); k++) {
					Object s = (Object) aList.get(k);
					System.out.println(s.toString()); 
				}
			}
			JSONObject companyObject = ws.jsonWebService("http://172.20.29.107:8080/FrontierWebService/UEN/INFO/GetUENInfo/"+companyUen);
			
			if (companyObject != null) {
				HashSet aSet = (HashSet)JSONUtil.getAllJsonKeys(companyObject);								
				Iterator<String> it = aSet.iterator();
				while(it.hasNext()) {
			        String key = it.next();
			        ArrayList aList = (ArrayList) JSONUtil.getValueOnly(key, companyObject);
			        companyDetailMap.put(key, aList);
			    }
			}		
			
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}
	}

	
	private static void testCapitalInfo() {
		WebServiceClientSample ws = new WebServiceClientSample();
		String str = "";
		try {
			
			//Print Value for Key
			JSONObject js = ws.jsonWebService("http://172.20.29.107:8080/FrontierWebService/UEN/INFO/GetUENInfo/201400161R");
			// str = "uenId"; //for String Test
			// str = "acraEntity.address.streetName"; //for Json Object Test
			str = "acraEntity.shareholders"; //for Json Array Test
			// str = "acraEntity.capitalInfo.streetName"; //for Wrong Key
			// str = "acraEntity.capitalInfo"; //for Json Object Test
			
			String strCapitalInfo = "acraEntity.capitalInfo";
			String shareTypeCode = "";
			String shareholderCategoryCode = "";
			String amount = "";
			ArrayList capitalInfoList = new ArrayList();
			ArrayList shareTypeCodeList = new ArrayList();
			ArrayList shareholderCategoryCodeList = new ArrayList();
			ArrayList amountList = new ArrayList();
			
			
			if (js != null) {
				capitalInfoList = (ArrayList) JSONUtil.getKeyAndValue(strCapitalInfo, js);
				for (int k=0 ; k<capitalInfoList.size(); k++) {
					JSONObject jsonObj = (JSONObject) capitalInfoList.get(k);
					System.out.println(jsonObj.toString());
					shareTypeCodeList = (ArrayList) JSONUtil.getValueOnly("shareTypeCode", jsonObj);
					shareholderCategoryCodeList = (ArrayList) JSONUtil.getValueOnly("shareCategory", jsonObj);
					amountList = (ArrayList) JSONUtil.getValueOnly("amount", jsonObj);
					if (shareTypeCodeList.size()>0) {
						shareTypeCode = (String)shareTypeCodeList.get(0);
					}
					if (shareholderCategoryCodeList.size()>0) {
						shareholderCategoryCode = (String)shareholderCategoryCodeList.get(0);
					}
					if (amountList.size()>0) {
						amount = (String)amountList.get(0);
					}
					System.out.println(shareTypeCode+"-----------"+shareholderCategoryCode+"-----------"+amount);
					shareTypeCode = "";
					shareholderCategoryCode = "";
					amount = "";
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
			
		}
	}
	
	
	
}