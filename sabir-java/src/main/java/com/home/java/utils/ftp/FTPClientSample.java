package com.home.java.utils.ftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.log4j.Logger;

public class FTPClientSample {

	private static final Logger logger = Logger.getLogger(FTPClientSample.class);
	
	private String host;
	private String userName;
	private String password;
	private String remoteDir;
	private String localDir;	
	private FTPClient ftpClient;
	
	public FTPClientSample(String host, String userName, String password){
		this.host = host;
		this.userName = userName;
		this.password = password;
	}
	
	/**
	 * Method to connect to FTP Server
	 * @throws ApplicationException
	 */
	public void connect() throws ApplicationException{
		ftpClient = new FTPClient();
		try {
			logger.info("Attempting to connect to ftp server : "+this.host);
			ftpClient.connect(this.host);
			
			if(ftpClient.isConnected()){
				logger.info("Connected to ftp server "+this.host);
			} else{
				logger.info("Unable to connect to ftp server "+this.host);
			}
			boolean loginSuccess = ftpClient.login(this.userName, this.password);		
			
			if(loginSuccess){
				logger.info("Logged on Successfully. ");
			} else{
				logger.info("Login failed - connecting to FTP server = "+this.host+", with credentials "+this.userName+"/"+this.password); 
			}
		} catch (SocketException e) {
			logger.error("Error : ", e);
			logger.info(e);
		} catch (IOException e) {
			logger.error("Error : ", e);
			logger.info(e);
		}
	}
	
	/**
	 * Method to disconnect from FTP Client
	 */
	public void disconnect(){
		try {
			if(ftpClient != null){
				ftpClient.logout();
				ftpClient.disconnect();
				logger.info("Disconnected successfully");
			}
		} catch (IOException e) {
			
		}
	}
	
	
	/**
	 * Method to put file to FTP Server
	 * @param fileName File to put to FTP Server
	 * @return return true if transferred successfully
	 * @throws ApplicationException throws Application Exception
	 */
	public boolean putFile(String fileName, StringBuffer replyStr) throws ApplicationException{
		boolean done = false;
		if(ftpClient == null || !ftpClient.isConnected() ){
			logger.info("Not connected to ftp server. ");
		}
		FileInputStream inStream = null;
		try {
			if(this.remoteDir == null){
				logger.info("Remote directory is empty. Please set the remote directory.");
			}		
			if(this.localDir != null){
				fileName = localDir+"/"+fileName;
			}
			
			//SET FILE TYPE
			ftpClient.setFileType(org.apache.commons.net.ftp.FTP.BINARY_FILE_TYPE);
			
			File f = new File(fileName);
			inStream = new FileInputStream(f);
			done = ftpClient.storeFile(this.remoteDir+"/"+f.getName(), inStream);
			//append the reply string
			replyStr.append(ftpClient.getReplyString());					
		} catch (FileNotFoundException e) {
			logger.error("FTP FileNotFoundException..", e);
			logger.info(e);
		} catch (IOException e) {
			logger.error("FTP IOException..", e);
			logger.info(e);
		}finally{
			try {
				if(inStream != null){
					inStream.close();
				}
			} catch (IOException e) {
				
			}
		}
		return done;
	}

	/**
	 * Method to getFile from FTP Server
	 * @param fileName File name to get 
	 * @return return true if file received successfully
	 * @throws ApplicationException throws Application Exception
	 */
	public boolean getFile(String fileName) throws ApplicationException{
		boolean done = false;
		if(ftpClient == null || !ftpClient.isConnected() ){
			logger.info("Not connected to ftp server. ");
		}
		FileOutputStream outStream = null; 
		try {
			if(this.localDir == null){
			  logger.info("Local Dir is empty. Please set a local directory");			 
			  return false; 
			}		
			if(this.localDir != null){
				fileName = localDir+"/"+fileName;
			}
			//logger.info("Getting file :"+fileName);
			File f = new File(fileName); 
			outStream = new FileOutputStream(f);
			ftpClient.retrieveFile(f.getName(), outStream);
			done = true;
		} catch (FileNotFoundException e) {
			logger.error("Error :", e);
			logger.info(e);
		} catch (IOException e) {
			logger.error("Error :", e);
			logger.info(e);
		} finally{
			try {
				if(outStream != null){
					outStream.close();
				}
			} catch (IOException e) {
				
			}
		}
		return done;
	}

	public String[] listNames(String dir) throws Exception {
		String s[]=null;
		if(ftpClient == null || !ftpClient.isConnected() ){
			logger.info("Not connected to ftp server. ");
		}
		try {
			if(dir == null) {
				return ftpClient.listNames();
			} else {
				return ftpClient.listNames(dir);
			}
		} catch (IOException e) {
			logger.error("Error :", e);
			logger.info(e);
		}
		
		return s;
	}
	
	public FTPFile[] listFiles(String dir) throws Exception {
		FTPFile f[]=null;
		if(ftpClient == null || !ftpClient.isConnected() ){
			logger.info("Not connected to ftp server. ");
		}
		try {
			if(dir == null) {
				return ftpClient.listFiles();
			} else {
				return ftpClient.listFiles(dir);
			}
		} catch (IOException e) {
			logger.error("Error :", e);
			logger.info(e);
		}
		
		return f;
	}
	
	public boolean changeWorkingDirectory(String dir) throws Exception {
		if(ftpClient == null || !ftpClient.isConnected() ){
			logger.info("Not connected to ftp server. ");
		}
		try {
			return ftpClient.changeWorkingDirectory(dir);
		} catch (IOException e) {
			logger.error("Error :", e);
			logger.info(e);
		}
		return false;
	}
	
	public boolean deleteFile(String fileName) throws Exception {
		if(ftpClient == null || !ftpClient.isConnected() ){
			logger.info("Not connected to ftp server. ");
		}
		try {
			return ftpClient.deleteFile(fileName);
		} catch (IOException e) {
			logger.error("Error :", e);
			logger.info(e);
		}
		return false;
	}
	
	public String getReplyString() {
		return ftpClient.getReplyString();
	}
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRemoteDir() {
		return remoteDir;
	}

	public void setRemoteDir(String remoteDir) {
		this.remoteDir = remoteDir; 
	}
		
	public String getLocalDir() {
		return localDir;
	}

	public void setLocalDir(String localDir) {
		this.localDir = localDir;
	}
}
