package com.home.java.utils.json;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

public class TestJson {

	
	public static void main(String[] args) {
		
		ArrayList<Dept> depts = constructDepartments();
		
		System.out.println("----------GSON-------------------");
		try {
			callingGson(depts);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("----------Manual-------------------");	
		try {
			callingManual(depts);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		
	}
	
	private static void callingGson(List<Dept> depts) throws JSONException{
		JSONObject mainObj = gsonConvertCollection2Json(depts) ;
		ArrayList<Dept> departments = gsonConvertJson2Collection(mainObj);		
	}
	
	
	private static JSONObject gsonConvertCollection2Json(List<Dept> depts) throws JSONException {
		JSONObject js = new JSONObject();
		//Object Collection to Json
		Gson gson = new Gson();
		String str = gson.toJson(depts);
		js = new JSONObject();
		js.put("Departments", str);
		//System.out.println(js.get("Departments").toString());
		return js;
	}
	
	private static ArrayList<Dept> gsonConvertJson2Collection(JSONObject js)throws JSONException {
		Gson gson = new Gson();
		ArrayList<Dept> depts = new ArrayList<Dept>();
		//Json to Object Array				
		String jsonStr = js.get("Departments").toString();		
		Dept[] myDepts;
		try {
			myDepts = gson.fromJson(jsonStr, Dept[].class);
			System.out.println(gson.toJson(myDepts));
			for (int i=0; i<myDepts.length;i++) {
				depts.add(myDepts[i]);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return depts;
	}
	
	

	private static void callingManual(List<Dept> depts) throws JSONException{
		JSONObject mainObj = manualConvertCollection2Json(depts) ;
		ArrayList<Dept> departments = manualConvertJson2Collection(mainObj) ;	
	}
	
	private static JSONObject manualConvertCollection2Json(List<Dept> depts) throws JSONException{
		//Object Collection to Json
		JSONObject joDept = new JSONObject();
		JSONObject joEmp = new JSONObject();		
		JSONArray jaDept = new JSONArray();
		JSONArray jaEmp = new JSONArray();		
		for (Dept dept: depts) {
			joDept = new JSONObject();		
			joDept.put("deptId", dept.getDeptId());
			joDept.put("deptName", dept.getDeptName());
			if (dept.getEmps()!=null) {
				jaEmp = new JSONArray();
				for (Emp emp: dept.getEmps()) {
					joEmp = new JSONObject();		
					joEmp.put("empId", emp.getEmpId());
					joEmp.put("empName", emp.getEmpName());
					joEmp.put("deptId", emp.getDeptId());
					jaEmp.put(joEmp);
				}
			}
			joDept.put("emps",jaEmp);
			jaDept.put(joDept);
		}
		JSONObject mainObj = new JSONObject();
		mainObj.put("Departments", jaDept);
		System.out.println(mainObj.get("Departments").toString());
		return mainObj;
		
	}
	
	private static ArrayList<Dept> manualConvertJson2Collection(JSONObject js) throws JSONException{
		ArrayList<Dept> depts = new ArrayList<Dept>();
		ArrayList<Emp> emps = new ArrayList<Emp>();
		JSONArray jaDept = js.getJSONArray("Departments");
		
		
		return depts;		
	}
	
	private static ArrayList<Dept> constructDepartments(){
		ArrayList<Dept> depts = new ArrayList<Dept>();
		ArrayList<Emp> emps = new ArrayList<Emp>();
		Emp e = new Emp();
		e.setEmpId("1");
		e.setEmpName("Emp1");
		e.setDeptId("10");
		emps.add(e);
		e = new Emp();
		e.setEmpId("2");
		e.setEmpName("Emp2");
		e.setDeptId("10");
		emps.add(e);
		
		Dept d =new Dept();
		d.setDeptId("10");
		d.setDeptName("Dept10");
		d.setEmps(emps);
		depts.add(d);
		
		emps = new ArrayList<Emp>();
		e = new Emp();
		e.setEmpId("3");
		e.setEmpName("Emp3");
		e.setDeptId("20");
		emps.add(e);
		
		d = new Dept();
		d.setDeptId("20");
		d.setDeptName("Dept20");
		d.setEmps(emps);
		depts.add(d);
		
		
		return depts;
	}
	
}

class Dept{
	private String deptId;
	private String deptName;
	private List<Emp> emps;
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public List<Emp> getEmps() {
		return emps;
	}
	public void setEmps(List<Emp> emps) {
		this.emps = emps;
	}
	
	
}

class Emp{
	private String empId;
	private String empName;
	private String deptId;
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	
}
