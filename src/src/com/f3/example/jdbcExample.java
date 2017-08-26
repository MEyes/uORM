package com.f3.example;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.junit.Test;

import com.mysql.jdbc.Driver;
import com.mysql.jdbc.PreparedStatement;

import jdk.nashorn.internal.runtime.ECMAErrors;

public class jdbcExample {
	
	
	private final String DRIVER_CLASS="com.mysql.jdbc.Driver";
	private final String URL="jdbc:mysql://localhost:3306/crm";
							  
	private final String USER="root";
	private final String PASSWORD="root";
	
	
	
	private Connection getConnection() throws Exception{
		
		
		Class.forName(DRIVER_CLASS);
		return DriverManager.getConnection(URL,USER,PASSWORD);
	}
	
	private void closeAll(Connection conn,Statement stmt,ResultSet rs){
		
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
	
	
	
	@Test
	public void testInsert() {
		
		Connection connection=null;
		Statement statement=null;
		
		try {
			connection=getConnection();
			statement=connection.createStatement();
			
			int rows = statement.executeUpdate("insert into users(name,address,birthday,salary,email) values('Jack','Shanghai,China','1990-09-22','10000','jack@crm.com')");
			
			System.out.println(rows);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			
			closeAll(connection, statement, null);
			
		}
		
	}
	
	
	@Test
	public void testDelete() {
		
		
		Connection connection=null;
		Statement statement=null;
		
		try {
			connection=getConnection();
			statement=connection.createStatement();
			
			int rows = statement.executeUpdate("delete from users where id=3");
			
			System.out.println(rows);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		finally {
			
			closeAll(connection, statement, null);
			
		}
		
	}
	
	@Test
	public void testSelect()  {
	
		
		Connection connection=null;
		Statement statement=null;
		ResultSet resultSet=null;
		
		try {
			connection=getConnection();
			statement=connection.createStatement();
			
			String sql="select * from users";
			//執行
			resultSet = statement.executeQuery(sql);
			//遍歷
			//ResultSet對象中包含一個游標，默認指向表的第一行數據之前
			//執行next方法，游標下移一行
			while(resultSet.next()){
				
				System.out.println("-------Begin----------");
				System.out.println(resultSet.getInt(1));
				System.out.println(resultSet.getString("name"));
				System.out.println(resultSet.getObject(3));
				System.out.println(resultSet.getDate("birthday"));
				System.out.println(resultSet.getDouble(5));
				System.out.println(resultSet.getObject(6));
				System.out.println("-------End----------");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		finally {
			
			closeAll(connection, statement, resultSet);
			
		}
		
	}

	
	@Test 
	public void testUpdate() throws Exception{
		
		
		Connection connection=null;
		Statement statement=null;
		
		try {
			connection=getConnection();
			statement=connection.createStatement();
			
			int rows = statement.executeUpdate("update users set salary='10001.01' where id=3");
			
			System.out.println(rows);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		finally {
			
			closeAll(connection, statement, null);
			
		}
		
	}
	
	@Test
	public void testPreparedStatement(){
		
		Connection connection=null;
		java.sql.PreparedStatement statement=null;
		
		try {
			connection=getConnection();
			
			String sql="insert into users(name,address,birthday,salary,email) values(?,?,?,?,?)";
			
			
			statement = connection.prepareStatement(sql);
			statement.setString(1, "jack");
			statement.setString(2, "china");
			statement.setDate(3, new Date(new java.util.Date ().getTime()));
			statement.setDouble(4, 12345.6f);
			statement.setString(5, "jack@crm.com");
			
			
			int rows = statement.executeUpdate();
			
			System.out.println(rows);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		finally {
			
			closeAll(connection, statement, null);
			
		}
		
		
	}

}
