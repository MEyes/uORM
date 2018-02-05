package com.f3.orm.type;

import java.sql.SQLException;

public class TypeHandlerRegistry {

	public static Class getClass(int columnType) {
		Class clazz = null;
		JdbcType jdbcType = JdbcType.forCode(columnType);
		switch (jdbcType) {
		case TINYINT:
		case SMALLINT:
		case INTEGER:
			clazz = Integer.class;
			break;

		case FLOAT:
			clazz = Float.class;
			break;
		case DOUBLE:
			clazz = Double.class;
			break;

		case VARCHAR:
			clazz = String.class;
			break;
		default:
			break;
		}

		return clazz;
	}

	public static TypeHandler getTypeHandler(int columnType) throws SQLException {
		Class clazz = getClass(columnType);
		TypeHandler handler = null;

		if (clazz == String.class) {
			handler = new StringTypeHandler();
		} else if (clazz == Integer.class) {
			handler = new IntegerTypeHandler();
		} else if (clazz == Float.class) {
			handler = new FloatTypeHandler();
		} else if (clazz == Double.class) {
			handler=new DoubleTypeHandler();
		}

		return handler;
	}

}