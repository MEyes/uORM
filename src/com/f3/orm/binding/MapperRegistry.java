package com.f3.orm.binding;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.f3.orm.session.Configuration;
import com.f3.orm.session.SqlSession;


public class MapperRegistry {

	private Configuration configuration;
	private final Map<Class<?>, MapperProxyFactory<?>> knownMappers = new HashMap();
	
	public MapperRegistry(Configuration configuration){
		this.configuration=configuration;
	}
	
	public <T> boolean hasMapper(Class<T> type) {
		return this.knownMappers.containsKey(type);
	}
	
	public void addMapper(Class type) throws Exception{
			if(type.isInterface()) {
				if (this.hasMapper(type)) {
					return;
					//throw new Exception("Type " + type+ " is already known to the MapperRegistry.");
				}
				this.knownMappers.put(type, new MapperProxyFactory(type));
			}
	}
	
	public <T> T getMapper(Class<T> type, SqlSession sqlSession) throws Exception{
		addMapper(type);
		MapperProxyFactory mapperProxyFactory = (MapperProxyFactory)this.knownMappers.get(type);
		if (mapperProxyFactory == null) {
			throw new Exception("Error getting mapperProxyFactory instance. ");
		} else {
			try {
				return (T)mapperProxyFactory.newInstance(sqlSession);
			} catch (Exception exception) {
				throw new Exception("Error getting mapper instance. Cause: " + exception, exception);
			}
		}
	}
	
	public Collection<Class<?>> getMappers() {
		return Collections.unmodifiableCollection(this.knownMappers.keySet());
	}

}
