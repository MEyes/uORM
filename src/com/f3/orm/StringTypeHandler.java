package com.f3.orm;

import java.lang.reflect.Type;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StringTypeHandler implements TypeHandler{

	@Override
	public Object getResult(ResultSet resultSet, String columnName) throws SQLException {
		return resultSet.getString(columnName);
	}

}
