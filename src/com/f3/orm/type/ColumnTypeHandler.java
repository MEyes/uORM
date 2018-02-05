package com.f3.orm.type;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.exceptions.MySQLTransientException;

public class ColumnTypeHandler {
	
    public static Class getClass(int columnType) {
        return TypeHandlerFactory.getClass(columnType);
    }

    public static Object getResult(ResultSet resultSet, String columnName, int columnType) throws SQLException {
        TypeHandler handler = null;

        handler = TypeHandlerFactory.getTypeHandler(columnType);
        if (handler == null) {
            throw new MySQLTransientException();
        }
        else {
            return handler.getResult(resultSet, columnName);
        }
    }
}
