package com.f3.orm;

import java.sql.SQLException;
import java.util.List;

public class SimpleExecutor implements Executor{


	
	@Override
	public boolean isAutoCommit() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setAutoCommit(boolean autoCommit) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T> List<T> query(BoundSql sql) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(BoundSql sql) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

}
