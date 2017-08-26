package com.f3.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBUtils {

	private static final String DRIVER_CLASS="com.mysql.jdbc.Driver";
	private static final String URL="jdbc:mysql://localhost:3306/crm";
							  
	private static final String USER="root";
	private static final String PASSWORD="root";
	
	
	
	public static Connection getConnection() throws Exception{
		
		
		Class.forName(DRIVER_CLASS);
		return DriverManager.getConnection(URL,USER,PASSWORD);
	}
	
	public static void closeAll(Connection conn,Statement stmt,ResultSet rs){
		
		if (conn!=null) {
			
			try {
				conn.close();
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		
			conn=null;
			
		}
		
		if (stmt!=null) {
			
			try {
				stmt.close();
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		
			stmt=null;
			
		}
		
		if (rs!=null) {
			
			try {
				rs.close();
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		
			rs=null;
			
		}
	}
}
