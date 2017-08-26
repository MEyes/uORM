package com.f3.example;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.Test;


public class DataSourceExample {
	@Test
	public void test_insert() throws SQLException{
		
		
		SimpleDataSource dataSource=new SimpleDataSource();
		PreparedStatement preparedStatement=null;
		Connection connection = dataSource.getConnection();
		try {
			
			preparedStatement=connection.prepareStatement("insert into users values(?,?,?,?)");
			
			String sql="insert into users(name,address,birthday,salary,email) values(?,?,?,?,?)";
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, "james");
			preparedStatement.setString(2, "china");
			preparedStatement.setDate(3, new Date(new java.util.Date ().getTime()));
			preparedStatement.setDouble(4, 12345.6f);
			preparedStatement.setString(5, "james@crm.com");
			
			int rows=preparedStatement.executeUpdate();
			System.out.println("rows:"+rows);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		finally{
			DBUtils.closeAll(connection, preparedStatement,null);
			
			
		}
		
	}

}
