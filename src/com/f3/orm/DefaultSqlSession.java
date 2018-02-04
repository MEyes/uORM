package com.f3.orm;

import java.util.List;

public class DefaultSqlSession implements SqlSession{

	private Executor executor;
	private Configuration configuration;
	
	public DefaultSqlSession(Executor executor,Configuration configuration) {
		this.executor=executor;
		this.configuration=configuration;
	}
	
	@Override
	public <T> T selectOne(String statement) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T selectOne(String statement, Object parameter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <E> List<E> selectList(String statement) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <E> List<E> selectList(String statement, Object parameter) {
		
		return null;
		  //MappedStatement ms = configuration.getMappedStatement(statement);
	      //List<E> result = executor.query(ms, wrapCollection(parameter), rowBounds, Executor.NO_RESULT_HANDLER);
	}

	@Override
	public int insert(String statement, Object parameter) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(String statement, Object parameter) {
		 //MappedStatement ms = configuration.getMappedStatement(statement);
	     //return executor.update(ms, wrapCollection(parameter));
		return 0;
	}

	@Override
	public int delete(String statement, Object parameter) {
		// TODO Auto-generated method stub
		return 0;
	}

}
