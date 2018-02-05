package com.f3.orm.binding;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

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
		MappedStatement executeStatement = SqlMapList.maps.get(method.getName());
		String sql=builder.buildSql(context, executeStatement);
		System.out.println(sql);
		
		return null;
	}
	
}
