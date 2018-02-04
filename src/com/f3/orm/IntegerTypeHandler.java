package com.f3.orm;

import java.sql.ResultSet;
import java.sql.SQLException;

public class IntegerTypeHandler implements TypeHandler{

	@Override
	public Object getResult(ResultSet resultSet, String columnName) throws SQLException {
		return resultSet.getInt(columnName);
	}

}