package com.f3.orm;

import java.sql.SQLException;
import java.util.List;

public interface SqlSession {

	<T> T selectOne(String sql,String className) throws SQLException;

	<T> T selectOne(String sql, Object parameter,String className)throws SQLException;

	<E> List<E> selectList(String sql,String className)throws SQLException;

	<E> List<E> selectList(String sql, Object parameter,String className)throws SQLException;

	int insert(String sql, Object parameter)throws SQLException;

	int update(String sql, Object parameter)throws SQLException;

	int delete(String sql, Object parameter)throws SQLException;

}
