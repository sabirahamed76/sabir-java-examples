package com.home.sabir.hibernate.Test;

import java.sql.Connection;
import java.sql.DriverManager;

public class HibernateTest {
    public static void main(String[] args){
        String jdbcurl = "jdbc:mysql://localhost:3306/asits?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
        String user = "root";
        String pass = "admin";

        try{
            System.out.println("Connection to Database...");

            Connection myConn = DriverManager.getConnection(jdbcurl, user, pass);

            System.out.println("Connection successful");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
