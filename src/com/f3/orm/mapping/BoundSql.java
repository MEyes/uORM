package com.f3.orm.mapping;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.util.LinkedHashMap;
import java.util.Map;

public class BoundSql {
	
	private String sql;
	private Map<Object, Class> mapping;
	private Class resultClazz;


    public BoundSql(String sql, Class clazz) {
        this.sql = sql;
        this.resultClazz = clazz;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public Class getResultClazz() {
        return resultClazz;
    }

    public void setResultClazz(Class resultClazz) {
        this.resultClazz = resultClazz;
    }

}
