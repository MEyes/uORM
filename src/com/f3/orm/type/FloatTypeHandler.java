package com.f3.orm.type;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FloatTypeHandler implements TypeHandler{

	@Override
	public Object getResult(ResultSet resultSet, String columnName) throws SQLException {
		return resultSet.getFloat(columnName);
	}

}
