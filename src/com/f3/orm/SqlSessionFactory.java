package com.f3.orm;

public class SqlSessionFactory {
	
	
	public SqlSession build(){
		return new DefaultSqlSession();
	}
}
