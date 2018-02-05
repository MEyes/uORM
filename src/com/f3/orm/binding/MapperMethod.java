package com.f3.orm.binding;

import java.lang.reflect.Method;

import com.f3.orm.session.Configuration;
import com.f3.orm.session.SqlSession;

public class MapperMethod {

	private Class<?> mapperInterface;
	private Method method;
	private Configuration configuration;
	
	public MapperMethod(Class<?> mapperInterface, Method method,Configuration configuration) {
			
		this.mapperInterface=mapperInterface;
		this.method=method;
		this.configuration=configuration;
	}
	
	public Object execute(SqlSession sqlSession, Object[] args) {
		return null;
		//sqlsession接口
	}
	
}
