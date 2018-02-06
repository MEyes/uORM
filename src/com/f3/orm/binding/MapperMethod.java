package com.f3.orm.binding;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.f3.orm.User;
import com.f3.orm.mapping.BoundSql;
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
		String statementId = mapperInterface.getName() + "." + method.getName();
		MappedStatement executeStatement = this.configuration.mappedStatements.get(statementId);
		Class parameterType = executeStatement.getParameterType();
		Class resultType=executeStatement.getResultType();
		
		Map<String,Object> parameters=new HashMap<>();
		Field[] declaredFields = parameterType.getDeclaredFields();
		List<Object> result=null;
		//TODO:特殊处理
		for (Field field : declaredFields) {
			try {
				parameters.put(field.getName(),field.get(args[0]) );
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}

		String sql=executeStatement.buildSql(parameters);
	
		switch (executeStatement.getSqlCommandType()) {
		case SELECT:
			BoundSql boundSql=new BoundSql(sql,resultType);
			
			try {
				//应该去sqlsession
				result=this.configuration.executor.query(boundSql);
		
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;

		default:
			break;
		}
		
		return result;
		
	}
	
	
}
