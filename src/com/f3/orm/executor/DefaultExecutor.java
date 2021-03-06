package com.f3.orm.executor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.f3.orm.mapping.BoundSql;
import com.f3.orm.session.Configuration;

public class DefaultExecutor implements Executor {

	private Configuration configuration;
	
	private boolean autoCommit;

	public DefaultExecutor(Configuration configuration) {
		this.configuration = configuration;
		setAutoCommit(true);
	}

	@Override
	public boolean isAutoCommit() {
		// TODO Auto-generated method stub
		return autoCommit;
	}

	@Override
	public void setAutoCommit(boolean autoCommit) {
		this.autoCommit = autoCommit;

	}

	@Override
	public <T> List<T> query(BoundSql sql) throws SQLException {
		List<T> list = null;
		Connection connection = null;
		try {
			connection = this.configuration.getDataSource().getConnection();
			if (autoCommit) {
				connection.setAutoCommit(true);
			} else {
				connection.setAutoCommit(false);
			}

			PreparedStatement prepareStatement = connection.prepareStatement(sql.getSql());
			StatementHandler statementHandler = new DefaultStatementHandler(prepareStatement);
			ResultSet resultSet = statementHandler.doQuery(sql);

			DefaultResultSetHandler resultSetHandler = new DefaultResultSetHandler(resultSet, sql);
			list = resultSetHandler.handleResultSet();

			resultSet.close();
			prepareStatement.close();
		} catch (Exception exception) {
			if (!autoCommit) {
				connection.rollback();
			}
			exception.printStackTrace();
		}finally{
			//close connection
			connection.close();
		}
		return list;
	}

	@Override
	public int update(BoundSql sql) throws SQLException {
		int result = 0;
		Connection connection = null;
		try {
			connection = this.configuration.getDataSource().getConnection();
			if (!autoCommit) {
				connection.setAutoCommit(false);
			}

			PreparedStatement statement = connection.prepareStatement(sql.getSql());
			StatementHandler statementHandler = new DefaultStatementHandler(statement);
			result = statementHandler.doUpdate(sql);
			if (!autoCommit) {
				connection.commit();
			}
			statement.close();
		} catch (Exception e) {
			if (!autoCommit) {
				connection.rollback();
			}
			e.printStackTrace();
		} finally {
			connection.close();
		}

		return result;
	}

}
