package com.home.java.security;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.home.java.utils.system.DiskUtils;

public class Licence {

	/*	
	 * CREATE TABLE sys_licence
	 * (
	 * 	   	id BIGINT PRIMARY KEY not null auto_increment,
	 * 		code VARCHAR(100),
	 * 		uid VARCHAR(100),
	 * 		licencestr VARCHAR(9999)
	 * );
	*/

	@SuppressWarnings("unused")
	private transient final Logger logger = Logger.getLogger(this.getClass());

	private static final SimpleDateFormat FORMAT_DATE =  new SimpleDateFormat("ddMMyyyy");
	
	 
	private static Properties configProp = new Properties();
    

    public void loadProps() {
        InputStream in = this.getClass().getResourceAsStream("dbinfo.properties");
        try {
            configProp.load(in);            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	public static String getSerialNumber(String drive) {
		String result = "";
	    try {
	      File file = File.createTempFile("realhowto",".vbs");
	      file.deleteOnExit();
	      FileWriter fw = new java.io.FileWriter(file);

	      String vbs = "Set objFSO = CreateObject(\"Scripting.FileSystemObject\")\n"
	                  +"Set colDrives = objFSO.Drives\n"
	                  +"Set objDrive = colDrives.item(\"" + drive + "\")\n"
	                  +"Wscript.Echo objDrive.SerialNumber";  // see note
	      fw.write(vbs);
	      fw.close();
	      Process p = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());
	      BufferedReader input =
	        new BufferedReader
	          (new InputStreamReader(p.getInputStream()));
	      String line;
	      while ((line = input.readLine()) != null) {
	         result += line;
	      }
	      input.close();
	    }
	    catch(Exception e){
	        e.printStackTrace();
	    }
	    return result.trim();
    }
	
	public static String createLicense(String code,String uid,String clients,String users,String expiryDate,String hid) {		
		StringBuffer licenseBuf = new StringBuffer();
		licenseBuf.append("code").append("=").append(code).append(",");
		licenseBuf.append("uid").append("=").append(uid).append(",");	
		licenseBuf.append("clients").append("=").append(users).append(",");
		licenseBuf.append("users").append("=").append(users).append(",");
		licenseBuf.append("expiry").append("=").append(expiryDate).append(",");
	    DESEncoder encoder = new DESEncoder(hid);
		String encodedString = encoder.encrypt(licenseBuf.toString());
		return encodedString ;
	}
	
	
	public static String getLicense(String license, String passPhrase) {
		if(license == null || license.trim().length() == 0) return null;
		DESDecoder decoder = new DESDecoder(passPhrase);
	    return decoder.decrypt(license);	 	    
	}
	
	private static Connection getDBConnection(String DB_DRIVER,String DB_CONNECTION,String  DB_USER,String DB_PASSWORD ) throws Exception{		  
		Class.forName(DB_DRIVER);
		Connection dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER,DB_PASSWORD);
		return dbConnection; 
	}
		
	private static void insertLicence(String code,String uid,String licenceStr,Connection conn) throws Exception {
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "";
		
		try {
			stmt = conn.createStatement();		
			sql = "insert into sys_licence values (1,'" + code + "','" + uid + "','" + licenceStr +  "')";
			stmt.executeUpdate(sql);
		}finally {
				if (rs != null){
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
			}
	}
	
	public static void main(String[] args){
		Calendar cal = Calendar.getInstance();
		Licence createLicence = new Licence();
		createLicence.loadProps();
		String DB_DRIVER = configProp.getProperty("DB_DRIVER");
		String DB_CONNECTION = configProp.getProperty("DB_CONNECTION");
		String DB_USER = configProp.getProperty("DB_USER");
		String DB_PASSWORD = configProp.getProperty("DB_PASSWORD");	
		String code = configProp.getProperty("CODE");
		String uid = configProp.getProperty("UID");
		String clients = configProp.getProperty("CLIENTS");
		String users = configProp.getProperty("USERS");
		String expiry = configProp.getProperty("EXPIRY");	
	    String hid = DiskUtils.getSerialNumber("C");
	    cal.add(Calendar.MONTH, new Integer(expiry).intValue());
	    String expiryDate = FORMAT_DATE.format(cal.getTime());
	    Connection conn = null;
	    
	    try {
	    	System.out.println("============== Licence Generated Started ====================");
	    	System.out.println("CODE = "+code);
	    	System.out.println("UID = "+uid);
	    	System.out.println("CLIENTS = "+clients);
	    	System.out.println("USERS = "+users);
	    	System.out.println("EXPIRY = "+expiryDate);	
	    	System.out.println("HARDDISK = "+hid);
		    
	    	conn = getDBConnection(DB_DRIVER,DB_CONNECTION,DB_USER,DB_PASSWORD);
	        conn.setAutoCommit(false);
	        
	        String licenceStr = Licence.createLicense(code,uid,clients,users,expiryDate,hid);
	        Licence.insertLicence(code,uid,licenceStr,conn);

			conn.commit();
			System.out.println("Licence = "+licenceStr);
			System.out.println("Dec = "+Licence.getLicense(licenceStr, hid));
			System.out.println("============== Licence Generated Finished ====================");
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
