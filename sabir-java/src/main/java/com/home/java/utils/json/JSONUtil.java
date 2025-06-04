package com.home.java.utils.json;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.StringTokenizer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONUtil {

	public static Object getKeyAndValue(String key, JSONObject jsonObj ) {
		return getValueForJsonKey(key, jsonObj, new ArrayList<Object>(), false );
	}
	
	public static Object getValueOnly(String key, JSONObject jsonObj ) {
		return getValueForJsonKey(key, jsonObj, new ArrayList<Object>(), true );
	}
	
	public static Integer countKeys(String name, JSONObject jsonObj ) {
		ArrayList aList = (ArrayList) getValueForJsonKey(name, jsonObj, new ArrayList<Object>(), true );
		Integer i = new Integer(0);
		if (aList!=null)
			i=aList.size();
		return i;
	}
	
	public static Object getAllJsonKeys(JSONObject jsonObj) throws JSONException{		
		return getJsonKeys(jsonObj, new HashSet<Object>(), new String(""));
	}
	
	//Private Methods
	private static Object getValueForJsonKey(String name, JSONObject jsonObj, ArrayList<Object> aList, boolean valueOnly ){
		//Intialize the variables
		int i=0,j=0,k=0;
		boolean fullObj = false;
		String propName = "";
		StringTokenizer st = new StringTokenizer(name,".");
		ArrayList<String> nameList = new ArrayList<String>();
		StringBuffer sb = new StringBuffer();
		Object obj= null;
		JSONObject jsObj = null;
		try {
			
			//Parse the Key String
			int noOfItr = st.countTokens();
			while(st.hasMoreTokens()) {
				String s=st.nextToken();
				nameList.add(s);
				j++;
				if (noOfItr==j)
					propName = s;
			}
			
			
			//Iterate the JSON Object
			for (i=0; i<noOfItr; i++) {
				if (jsonObj.isNull(nameList.get(0))){
					nameList.remove(0);
					if (valueOnly)
						aList.add("");	
					else
						aList.add("\""+ propName + "\":\"\"");	
					break;
				} else{
					obj = jsonObj.get(nameList.get(0));
					if (obj instanceof String) {
						nameList.remove(0);
						if (valueOnly)
							aList.add(obj);	
						else
							aList.add("\""+ propName + "\":\""+ obj +"\"");	
						continue;
					}else if (obj instanceof JSONObject) {
						nameList.remove(0);
						jsonObj = (JSONObject) obj;
					}else if (obj instanceof JSONArray) {
						if (propName.equals(nameList.get(0))) fullObj=true;
						nameList.remove(0);
						JSONArray jsArr = (JSONArray)obj;
						for (j=0 ; j<nameList.size(); j++) {
							if (j!=0)sb.append(",");
							sb.append(nameList.get(j));
						}
						for (k=0 ; k<jsArr.length(); k++) {
							jsObj = (JSONObject) jsArr.get(k);
							if (fullObj)
								aList.add(jsObj);
							else	
								getValueForJsonKey(sb.toString(),jsObj,aList,valueOnly);
						}
					}
				}
			}		
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return aList;
	}
	
	
	
	private static Object getJsonKeys(JSONObject jsonObj, HashSet<Object> aSet, String parent) throws JSONException{
		//Intialize the variables
		String str = "";
		Object obj= null;
		JSONObject jsObj = null;
		
		Iterator it = jsonObj.keys();
		while (it.hasNext()) {
			str = (String) it.next();
			obj = jsonObj.get(str);		
			
			if (obj instanceof String) {
				if (parent.equals("")) {
					aSet.add(str);
				}else {
					aSet.add(parent + "." + str);
				}
			}else if (obj instanceof JSONObject) {
				if (parent.equals("")) {
					aSet.add(str);
					getJsonKeys((JSONObject) obj,aSet,str);
				}else {
					aSet.add(parent + "." + str);
					getJsonKeys((JSONObject) obj,aSet,parent + "." + str);
				}				
			}else if (obj instanceof JSONArray) {
				JSONArray jsArr = (JSONArray)obj;
				if (parent.equals("")) {
					aSet.add(str);
				}else {
					aSet.add(parent + "." + str);
				}
				for (int i=0 ; i<jsArr.length(); i++) {
					jsObj = (JSONObject) jsArr.get(i);
					if (parent.equals("")) {
						getJsonKeys(jsObj,aSet,parent + "." + str);
					}else {
						getJsonKeys(jsObj,aSet,parent + "." + str);
					}
				}					
			}	
		}
		
		return aSet;
			
	}
		
		
	
}
