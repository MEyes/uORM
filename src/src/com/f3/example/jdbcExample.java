package com.f3.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

public class jdbcExample {
	
	
	@Test
	public void testjdbc()  throws SQLException, ClassNotFoundException {
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
			
			System.out.println(resultSet.getObject(1));
			System.out.println(resultSet.getObject(2));
			System.out.println(resultSet.getObject(3));
			System.out.println(resultSet.getObject(4));
			System.out.println(resultSet.getObject(5));
			System.out.println(resultSet.getObject(6));
		}
		
		resultSet.close();
		statement.close();
		connection.close();
	}


}
