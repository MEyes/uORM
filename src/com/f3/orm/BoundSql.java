package com.f3.orm;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.util.LinkedHashMap;
import java.util.Map;

public class BoundSql {
	
	private String sql;
	private Map<Object, Class> mapping;
	private Class resultClazz;

    public BoundSql() {
        this.sql = "";
        this.mapping = new LinkedHashMap<Object, Class>();
        this.resultClazz = null;
    }

    public BoundSql(String sql, Map<Object, Class> mapping, Class clazz) {
        this.sql = sql;
        this.mapping = mapping;
        this.resultClazz = clazz;
    }

    public BoundSql(String sql, Object value, Class clazz) {
        this(sql, new LinkedHashMap<Object, Class>(), clazz);

        if (value != null) {
            this.mapping.put(value, value.getClass());
        }
    }
    public void addMapping(Object value, Class clazz) {
        this.mapping.put(value, clazz);
    }

    public void updatePrepareStatement(PreparedStatement statement) throws SQLException {
        if (this.mapping.size() > 0) {
            int index = 1;

            for (Map.Entry<Object, Class> entry : mapping.entrySet()) {
                updatePrepareStatementInternal(statement, entry, index++);
            }
        }
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public Map<Object, Class> getMapping() {
        return mapping;
    }

    public void setMapping(Map<Object, Class> mapping) {
        this.mapping = mapping;
    }

    public Class getResultClazz() {
        return resultClazz;
    }

    public void setResultClazz(Class resultClazz) {
        this.resultClazz = resultClazz;
    }

    private void updatePrepareStatementInternal(PreparedStatement statement, Map.Entry<Object, Class> entry, int index) throws SQLException {
        Object key   = entry.getKey();
        Class  clazz = entry.getValue();

        if (clazz == String.class) {
            statement.setString(index, key.toString());
        }
        else if (clazz == Integer.class) {
            statement.setInt(index, Integer.valueOf(key.toString()));
        }
        else if (clazz == Long.class) {
            statement.setLong(index, Long.valueOf(key.toString()));
        }
        else {
            throw new SQLWarning();
        }
    }

}
