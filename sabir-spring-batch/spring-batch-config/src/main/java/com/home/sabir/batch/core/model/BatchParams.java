package com.home.sabir.batch.core.model;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BatchParams {
	
	private Map<String, Object> params;
	
	public BatchParams(String paramStr) {
		if(params == null) {
			params = new HashMap<>();
		}
		if (paramStr!=null && !paramStr.equals("")) {
			StringTokenizer st = new StringTokenizer (paramStr,",");
			while(st.hasMoreTokens()) {
				String s= st.nextToken();
				String key=s.substring(0, s.indexOf(":"));
				String val=s.substring(s.indexOf(":")+1,s.length());			
				params.put(key, val);
				
			}
		}
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}
	
	public Object get(String key) {
		if(params != null && params.containsKey(key)){
			return params.get(key);
		}
		return null;
	}
	
	public void put(String key, Object value) {
		if(params == null) {
			params = new HashMap<>();
		}
		params.put(key, value);
	}

}
