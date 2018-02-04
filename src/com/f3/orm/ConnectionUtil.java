package com.f3.orm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	
	public static final String URL = "jdbc:mysql://localhost:3306/user";
	public static final String USER = "root";
	public static final String PASSWORD = "p@ssw0rd";
	
	 public static Connection getConnection() {
	        Connection conn = null;
	        try {
	            conn = DriverManager.getConnection(URL, USER, PASSWORD);
	        }
	        catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return conn;
	    }
}
