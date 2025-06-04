package com.home.java.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class DML {
	
	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/asits";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "admin";
	private static final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
 
	public static void main(String[] argv) {
 
		try {
 
			selectRecordsFromDbUserTable();
			//insertRecordIntoDbUserTable();
			//updateRecordIntoDbUserTable();
			//selectRecordsByPrepStmt();
			//deleteRecordFromDbUserTable();
			//selectRecordsFromDbUserTable();
 
		} catch (SQLException e) {
 
			System.out.println(e.getMessage());
 
		}
 
	}
 
	private static void selectRecordsFromDbUserTable() throws SQLException {
		 
		Connection dbConnection = null;
		Statement statement = null;
		ResultSet rs = null;
 
		String selectTableSQL = "SELECT id, userName from admin_user";
 
		try {
			dbConnection = getDBConnection();
			System.out.println(dbConnection);
			statement = dbConnection.createStatement();
 
			System.out.println(selectTableSQL);
 
			// execute select SQL stetement
			rs = statement.executeQuery(selectTableSQL);
 
			while (rs.next()) {
 
				System.out.println("ID = " + rs.getString("id") + " - Name = " + rs.getString("userName"));
 
			}
 
		} catch (SQLException e) {
 
			System.out.println(e.getMessage());
 
		} finally {
 
			if (rs != null){
				rs.close();
			}
			
			if (statement != null) {
				statement.close();
			}
 
			if (dbConnection != null) {
				dbConnection.close();
			}
 
		}
 
	}
	
	private static void selectRecordsByPrepStmt() throws SQLException {
		 
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
 
		String selectSQL = "SELECT client_code, client_name FROM client WHERE client_code = ?";
 
		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(selectSQL);
			preparedStatement.setString(1, "1");
 
			// execute select SQL stetement
			ResultSet rs = preparedStatement.executeQuery();
 
			while (rs.next()) {
 
				System.out.println("Client Code = " + rs.getString("client_code") + " - Client Name = " + rs.getString("client_name"));
 
			}
 
		} catch (SQLException e) {
 
			System.out.println(e.getMessage());
 
		} finally {
 
			if (preparedStatement != null) {
				preparedStatement.close();
			}
 
			if (dbConnection != null) {
				dbConnection.close();
			}
 
		}
 
	}
	
	private static void insertRecordIntoDbUserTable() throws SQLException {
		 
		Connection dbConnection = null;
		Statement statement = null;
 
		String insertTableSQL = "INSERT INTO client"
				+ "(client_code, client_name,CREATED_DATE) " + "VALUES"
				+ "(1,'home', " + "to_date('"
				+ getCurrentTimeStamp() + "', 'yyyy/mm/dd hh24:mi:ss'))";
 
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
 
			System.out.println(insertTableSQL);
 
			// execute insert SQL stetement
			statement.executeUpdate(insertTableSQL);
 
			System.out.println("Record is inserted into client table!");
 
		} catch (SQLException e) {
 
			System.out.println(e.getMessage());
 
		} finally {
 
			if (statement != null) {
				statement.close();
			}
 
			if (dbConnection != null) {
				dbConnection.close();
			}
 
		}
 
	}
	
	private static void updateRecordIntoDbUserTable() throws SQLException {
		 
		Connection dbConnection = null;
		Statement statement = null;
 
		String updateTableSQL = "UPDATE client"
				+ " SET client_name = 'home1' "
				+ " WHERE client_code = 1";
 
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
 
			System.out.println(updateTableSQL);
 
			// execute update SQL stetement
			statement.execute(updateTableSQL);
 
			System.out.println("Record is updated to client table!");
 
		} catch (SQLException e) {
 
			System.out.println(e.getMessage());
 
		} finally {
 
			if (statement != null) {
				statement.close();
			}
 
			if (dbConnection != null) {
				dbConnection.close();
			}
 
		}
 
	}
	
	private static void deleteRecordFromDbUserTable() throws SQLException {
		 
		Connection dbConnection = null;
		Statement statement = null;
 
		String deleteTableSQL = "DELETE client WHERE client_code = 1";
 
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
 
			System.out.println(deleteTableSQL);
 
			// execute delete SQL stetement
			statement.execute(deleteTableSQL);
 
			System.out.println("Record is deleted from client table!");
 
		} catch (SQLException e) {
 
			System.out.println(e.getMessage());
 
		} finally {
 
			if (statement != null) {
				statement.close();
			}
 
			if (dbConnection != null) {
				dbConnection.close();
			}
 
		}
 
	}
 
	private static Connection getDBConnection() {
 
		Connection dbConnection = null; 
		try { 
			Class.forName(DB_DRIVER);
			dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER,DB_PASSWORD);
			return dbConnection; 
		} catch (SQLException e) { 
			System.out.println(e.getMessage()); 
		} catch (ClassNotFoundException e) { 
			System.out.println(e.getMessage()); 
		} 
		return dbConnection;
 
	}
	
	private static String getCurrentTimeStamp() {
		 
		java.util.Date today = new java.util.Date();
		return dateFormat.format(today.getTime());
 
	}
}
