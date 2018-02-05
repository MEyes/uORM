package com.f3.orm.executor;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public interface ResultSetHandler {
	<T> List<T> handleResultSet() throws SQLException;

	<E> List<E> handleResultSets(Statement stmt) throws SQLException;
}
