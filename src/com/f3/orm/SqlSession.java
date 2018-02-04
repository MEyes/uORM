package com.f3.orm;

import java.util.List;

public interface SqlSession {

	<T> T selectOne(String statement);

	<T> T selectOne(String statement, Object parameter);

	<E> List<E> selectList(String statement);

	<E> List<E> selectList(String statement, Object parameter);

	int insert(String statement, Object parameter);

	int update(String statement, Object parameter);

	int delete(String statement, Object parameter);

}
