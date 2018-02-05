package com.f3.orm.binding;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.binding.MapperMethod.SqlCommand;

import com.f3.orm.Context;
import com.f3.orm.SqlBuilder;
import com.f3.orm.SqlMapList;
import com.f3.orm.mapping.MappedStatement;
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
		//return null;
		//sqlsession接口
		Context context=new Context();
		context.setSqlId(method.getName());
		Map<String,Object> parameters=new HashMap<>();
		parameters.put("id",args[0]);
		context.setPrameters(parameters);
		SqlBuilder builder=new SqlBuilder();
		//全限定名
		String statementId = mapperInterface.getName() + "." + method.getName();
		MappedStatement executeStatement = this.configuration.mappedStatements.get(statementId);
		
		Parameter[] parameters2 = method.getParameters();
		for (Parameter parameter : parameters2) {
			System.out.println(parameter.getName());
		}
		
		String sql=builder.build(context, executeStatement);
		//this.configuration.executor.e
		
		return null;
	}
	
	
}
