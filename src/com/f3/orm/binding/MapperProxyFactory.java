package com.f3.orm.binding;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.f3.orm.session.SqlSession;

public class MapperProxyFactory<T> {

	private final Class<T> mapperInterface;
	
	private Map<Method, MapperMethod> methodCache = new ConcurrentHashMap();

	public MapperProxyFactory(Class<T> mapperInterface) {
		this.mapperInterface = mapperInterface;
	}

	public Class<T> getMapperInterface() {
		return this.mapperInterface;
	}
	
	public Map<Method, MapperMethod> getMethodCache() {
		return this.methodCache;
	}

	protected T newInstance(MapperProxy mapperProxy) {
		return (T)Proxy.newProxyInstance(this.mapperInterface.getClassLoader(),new Class[] { this.mapperInterface }, mapperProxy);
	}

	public T newInstance(SqlSession sqlSession) {
		MapperProxy mapperProxy = new MapperProxy(sqlSession,this.mapperInterface, this.methodCache);
		return this.newInstance(mapperProxy);
	}
}
