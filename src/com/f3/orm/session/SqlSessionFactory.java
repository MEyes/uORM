package com.f3.orm.session;

public class SqlSessionFactory {
	
	private Configuration configuration;
	public SqlSessionFactory(Configuration configuration) {
		this.configuration=configuration;
	}
	public SqlSession build(){
		return new SqlSession(configuration);
	}
}
