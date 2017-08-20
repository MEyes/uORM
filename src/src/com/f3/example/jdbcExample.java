package com.f3.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.junit.Test;

import jdk.nashorn.internal.runtime.ECMAErrors;

public class jdbcExample {
	
	
	
	@Test
	public void testInsert() throws Exception{
		
		Class.forName("com.mysql.jdbc.Driver");
		
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/crm?user=root&password=root");
		
		Statement statement = connection.createStatement();
		
		int rows = statement.executeUpdate("insert into users(name,address,birthday,salary,email) values('Jack','Shanghai,China','1990-09-22','10000','jack@crm.com')");
		
		System.out.println(rows);
		
		statement.close();
		connection.close();
		
	}
	
	@Test
	public void testDelete() throws Exception{
		
		Class.forName("com.mysql.jdbc.Driver");
		
		Properties properties=new Properties();
		properties.setProperty("user", "root");
		properties.setProperty("password", "root");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/crm",properties);
		
		Statement statement = connection.createStatement();
		
		int rows = statement.executeUpdate("delete from users where id=2");
		
		System.out.println(rows);
		
		statement.close();
		connection.close();
	}
	
	@Test
	public void testSelect()  throws SQLException, ClassNotFoundException {
		//注冊驅動
		
		//DriverManager.registerDriver(new Driver());
		Class.forName("com.mysql.jdbc.Driver");
		//建立連接
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/crm","root","root");
		//牀建執行sql的statement對象
		Statement statement = connection.createStatement();
		String sql="select * from users";
		//執行
		ResultSet resultSet = statement.executeQuery(sql);
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
		
		resultSet.close();
		statement.close();
		connection.close();
	}

	
	@Test 
	public void testUpdate() throws Exception{
		
		Class.forName("com.mysql.jdbc.Driver");
		
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/crm?user=root&password=root");
		
		Statement statement = connection.createStatement();
		
		int rows = statement.executeUpdate("update users set salary='10001.01' where id=2");
		
		
		System.out.println(rows);
		statement.close();
		connection.close();
	}

}
