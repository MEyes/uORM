package com.f3.orm.session;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import com.f3.orm.SqlBuilder;
import com.f3.orm.binding.MapperRegistry;
import com.f3.orm.executor.DefaultExecutor;
import com.f3.orm.executor.Executor;
import com.f3.orm.mapping.MappedStatement;
import com.f3.orm.type.TypeHandlerRegistry;

public class Configuration {

	public final MapperRegistry mapperRegistry=new MapperRegistry(this);
	public final TypeHandlerRegistry typeHandlerRegistry = new TypeHandlerRegistry();
	public final Map<String, MappedStatement> mappedStatements = new HashMap<String,MappedStatement>();
	public final SqlBuilder sqlBuilder=new SqlBuilder();
	public final Executor executor=new DefaultExecutor(this);
	
	private String driver;
	private String url;
	private String user;
	private String password;
	private DataSource dataSource;
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public DataSource getDataSource() {
		return dataSource;
	}
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
		try {
			return mapperRegistry.getMapper(type, sqlSession);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
