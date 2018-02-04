package com.f3.orm;

public class SqlSessionFactoryBuilder {
	

	public SqlSessionFactoryBuilder(){
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public SqlSessionFactory build(){
		
		return new SqlSessionFactory(new Configuration());
	}
}
