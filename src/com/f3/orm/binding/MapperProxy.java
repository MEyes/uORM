package com.f3.orm.binding;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.f3.orm.session.SqlSession;

public class MapperProxy implements InvocationHandler,Serializable{

	private static final long serialVersionUID = 1698463034443344765L;

	private final Class<?> mapperInterface;
	
	private final SqlSession sqlSession;
	
	private Map<Method, MapperMethod> methodCache;
	
	public  MapperProxy(SqlSession sqlSession,Class<?> mapperInterface,Map<Method, MapperMethod> methodCache){
		this.mapperInterface=mapperInterface;
		this.methodCache=methodCache;
		this.sqlSession=sqlSession;
	}
	
	public Object invoke(Object proxy, Method method, Object[] args)throws Throwable {
			
		if(method.getDeclaringClass().getClass().getSimpleName()=="Object"){//ToString???
			return method.invoke(this, args);
		}
		MapperMethod handler=cacheMapperMethod(method);
		return handler.execute(sqlSession, args);
	}
	
	public MapperMethod cacheMapperMethod(Method method){
		MapperMethod result=methodCache.get(method);
		if(result==null){
			result=new MapperMethod(mapperInterface,method,sqlSession.getConfiguration());
			methodCache.put(method,result);
		}
		return result;
	}
	
	
	
	
	
	
	

}
