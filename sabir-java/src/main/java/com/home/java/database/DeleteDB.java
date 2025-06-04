package com.home.java.database;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;


class DeleteDBForm implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String idCsv;	
	private Long txnCount;
	
	public String getIdCsv() {
		return idCsv;
	}
	public void setIdCsv(String idCsv) {
		this.idCsv = idCsv;
	}
	public Long getTxnCount() {
		return txnCount;
	}
	public void setTxnCount(Long txnCount) {
		this.txnCount = txnCount;
	}
	
	
}


public class DeleteDB {
	
	private static final long serialVersionUID = 1L;
	
	private static List<SimpleDateFormat>	
	    dateFormats = new ArrayList<SimpleDateFormat>() {{
	    add(new SimpleDateFormat("ddMMyyyy"));
	    add(new SimpleDateFormat("M/dd/yyyy"));
	    add(new SimpleDateFormat("dd.M.yyyy"));
	    add(new SimpleDateFormat("M/dd/yyyy hh:mm:ss a"));
	    add(new SimpleDateFormat("dd.M.yyyy hh:mm:ss a"));
	    add(new SimpleDateFormat("dd.MMM.yyyy"));
	    add(new SimpleDateFormat("dd-MMM-yyyy"));
	    add(new SimpleDateFormat("dd-MM-yyyy"));
	}
	};
	
	 
	private static Properties configProp = new Properties();
    

    public void loadProps() {
        InputStream in = this.getClass().getResourceAsStream("dbinfo.properties");
        try {
            configProp.load(in);            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
	public static void main(String[] argv) {
		
		DeleteDB deleteDB = new DeleteDB();
		deleteDB.loadProps();
		String DB_DRIVER = configProp.getProperty("DB_DRIVER");
		String DB_CONNECTION = configProp.getProperty("DB_CONNECTION");
		String DB_USER = configProp.getProperty("DB_USER");
		String DB_PASSWORD = configProp.getProperty("DB_PASSWORD");
		Connection conn = null;
		try {
			if (!validateArgs(argv)){
				showSyntax();
				return ;
			}	
			String accountCode = argv[0];
			String dt = argv[1];
			SimpleDateFormat st = getDateFormat(dt);
	        Date dt1 = st.parse(dt);
		        
	        conn = getDBConnection(DB_DRIVER,DB_CONNECTION,DB_USER,DB_PASSWORD);
	        conn.setAutoCommit(false);
		        
	        deleteDB(accountCode,dt1, conn);

				
			//COMMIT @ THE END
			conn.commit();
			System.err.println("====================ACTION COMPLETED=========================");
		} catch (Exception e) { 
			System.err.println("====================FINAL CATCH =============================");
			e.printStackTrace();
			
			try {
				if(conn != null) {
					conn.rollback();
				}
			} catch (SQLException e1) {}
		}
 
	}
	
	
	
	public static DeleteDBForm getTransData(String clientCode, Date txnEndDate, boolean countQuery, Connection dbConnection) throws Exception {
		PreparedStatement prepStmt = null;
		ResultSet rs = null;
		StringBuffer sqlBuf = new StringBuffer("");
		
		DeleteDBForm result = new DeleteDBForm();
		try {
					
			sqlBuf.append("select ");
			if(countQuery) {
				sqlBuf.append("count(1) as txnCount ");
			} else {
				sqlBuf.append("acc.account_id as accountId ");
			}
			sqlBuf.append("from sys_account acc ")
				.append("where acc.created_date <= ? ");
			
			
			prepStmt = dbConnection.prepareStatement(sqlBuf.toString());
			prepStmt.setString(1,clientCode);
			prepStmt.setDate(2,getSqlDate(txnEndDate));
			
			rs = prepStmt.executeQuery();
			 
			
			String accountId = "";
			int count = 0;
			if(countQuery) {
				while (rs.next()) {
					result.setTxnCount(rs.getLong("txnCount")) ;
					break;
				}
			} else {
				while (rs.next()) {
					count++;
					accountId = accountId + rs.getLong("accountId") + ",";
				}
				if (count>0){
					result.setIdCsv(accountId.substring(0, accountId.length()-1));
				}
			}
		} finally {
			if (rs != null){
				rs.close();
			}
			if (prepStmt != null) {
				prepStmt.close();
			}
			
		}	
		return result;
	}


	
	public static Long getAccountId(String accountCode, Connection dbConnection) throws Exception {
		
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "";
		Long clientId = 0L;
 
		try {
			stmt = dbConnection.createStatement();
			
			sql = "select id as accountId from sys_account where code in ('"+accountCode+"')";	
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				clientId = rs.getLong("accountId");
			}
			
		}  finally {
			if (rs != null){
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			
		}
		return clientId;
	}

	public static void deleteDB(String accountCode, Date txnEndDate, Connection dbConnection) throws Exception {
				
		Long accountId = getAccountId(accountCode, dbConnection);
		DeleteDBForm txnDataForm = getTransData(accountCode, txnEndDate, false, dbConnection);
		System.err.println("ACCOUNT ID  = " + accountCode +"(" + accountId + ")");
		
		purgeTables(accountId, dbConnection);
		
			
	}
	
	private static void purgeTables(Long accountId, Connection dbConnection) throws Exception {
		
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "";			
		int count = 0;
 
		try {
			stmt = dbConnection.createStatement();
			
			sql = "delete from sys_user where account_id in ("+accountId+")";
			count = stmt.executeUpdate(sql);		
			System.out.println("Records deleted from SYS USER = " + count);
	   }  finally {
			if (rs != null){
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
		}
		
	}
	
	private static Connection getDBConnection(String DB_DRIVER,String DB_CONNECTION,String  DB_USER,String DB_PASSWORD ) throws Exception{
  
		Class.forName(DB_DRIVER);
		Connection dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER,DB_PASSWORD);
		return dbConnection; 
	}

	private static java.sql.Date getSqlDate(Date d) {
	    return new java.sql.Date(d.getTime());
	}
	
	public static SimpleDateFormat getDateFormat(String input) {
	    Date date = null;
	    if(input == null) {
	        return null;
	    }
	    for (SimpleDateFormat format : dateFormats) {
	        try {
	            format.setLenient(false);
	            date = format.parse(input);
	        } catch (ParseException e) {
	        }
	        if (date != null) {
	        	return format;
	        }
	    }

	    return null;
	}
	

	public static boolean validateArgs(String[] argv){

		try{
			
			int length = argv.length;
	        if (length <= 0 || length > 2) {
	        	return false;
	        }
	        String accCode = argv[0];
	        String dt = argv[1];	        
	        
	        if ((accCode.toLowerCase().equals(""))){
	        	return false;
			}
	        
	        SimpleDateFormat st = getDateFormat(dt);
	        if (st == null){
	        	return false;
	        }
	        
		} catch (Exception e){
			return false;
		}
		
		return true;
	    
	}
	
	public static void showSyntax(){
		System.out.println("Usage: DeleteDB <account> <date>");
		System.out.println("	-accountcode Account Code");
        System.out.println("	-date		 Earlier records from the specified date (DD-MM-YYYY)");
	}
}
