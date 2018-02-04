package com.f3.orm;

public class SqlSessionFactory {
	
	private Configuration configuration;
	public SqlSessionFactory(Configuration configuration) {
		this.configuration=configuration;
	}
	public SqlSession build(){
		return new DefaultSqlSession(new SimpleExecutor(),this.configuration);
	}
}
