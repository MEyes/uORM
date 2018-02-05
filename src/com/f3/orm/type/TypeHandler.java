package com.f3.orm.type;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface TypeHandler {

	 Object getResult(ResultSet resultSet, String columnName) throws SQLException;
}
