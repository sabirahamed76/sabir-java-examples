package com.home.java.designpattern.structural;

import java.sql.Connection;

public class FacadeDemo {

	public static void main(String[] args) {
		String tableName="Employee";
		
		//generating MySql HTML report and Oracle PDF report without using Facade
		Connection con = MySqlHelper.getMySqlDBConnection();
		MySqlHelper mySqlHelper = new MySqlHelper();
		mySqlHelper.generateMySqlHTMLReport(tableName, con);
		
		Connection con1 = OracleHelper.getOracleDBConnection();
		OracleHelper oracleHelper = new OracleHelper();
		oracleHelper.generateOraclePDFReport(tableName, con1);
		
		//generating MySql HTML report and Oracle PDF report using Facade
		HelperFacade.generateReport(HelperFacade.DBTypes.MYSQL, HelperFacade.ReportTypes.HTML, tableName);
		HelperFacade.generateReport(HelperFacade.DBTypes.ORACLE, HelperFacade.ReportTypes.PDF, tableName);
	}

}

class MySqlHelper {
	
	public static Connection getMySqlDBConnection(){
		//get MySql DB connection using connection parameters
		return null;
	}
	
	public void generateMySqlPDFReport(String tableName, Connection con){
		//get data from table and generate pdf report
	}
	
	public void generateMySqlHTMLReport(String tableName, Connection con){
		//get data from table and generate pdf report
	}
}

class OracleHelper {

	public static Connection getOracleDBConnection(){
		//get Oracle DB connection using connection parameters
		return null;
	}
	
	public void generateOraclePDFReport(String tableName, Connection con){
		//get data from table and generate pdf report
	}
	
	public void generateOracleHTMLReport(String tableName, Connection con){
		//get data from table and generate pdf report
	}
	
}

class HelperFacade {

	public static void generateReport(DBTypes dbType, ReportTypes reportType, String tableName){
		Connection con = null;
		switch (dbType){
		case MYSQL: 
			con = MySqlHelper.getMySqlDBConnection();
			MySqlHelper mySqlHelper = new MySqlHelper();
			switch(reportType){
			case HTML:
				mySqlHelper.generateMySqlHTMLReport(tableName, con);
				break;
			case PDF:
				mySqlHelper.generateMySqlPDFReport(tableName, con);
				break;
			}
			break;
		case ORACLE: 
			con = OracleHelper.getOracleDBConnection();
			OracleHelper oracleHelper = new OracleHelper();
			switch(reportType){
			case HTML:
				oracleHelper.generateOracleHTMLReport(tableName, con);
				break;
			case PDF:
				oracleHelper.generateOraclePDFReport(tableName, con);
				break;
			}
			break;
		}
		
	}
	
	public static enum DBTypes{
		MYSQL,ORACLE;
	}
	
	public static enum ReportTypes{
		HTML,PDF;
	}
}

