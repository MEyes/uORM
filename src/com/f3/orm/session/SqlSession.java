package com.f3.orm.session;

import java.sql.SQLException;
import java.sql.SQLWarning;
import java.util.List;

import com.f3.orm.Executor;
import com.f3.orm.SimpleExecutor;
import com.f3.orm.mapping.BoundSql;

public class SqlSession{

	private Executor executor;
	private Configuration configuration;

    public SqlSession(Configuration configuration) {
        this.configuration = configuration;
        this.executor = new SimpleExecutor(configuration);
    }


    public boolean isAutoCommit() {
        return executor.isAutoCommit();
    }

    public void setAutoCommit(boolean autoCommit) {
        executor.setAutoCommit(autoCommit);
    }


    public <T> T selectOne(String sql, String className) throws SQLException {
        return selectOne(sql, null, className);
    }

    public <T> T selectOne(String sql, Class<T> resultClazz) throws SQLException {
        return selectOne(sql, null, resultClazz);
    }

    public <T> T selectOne(String sql, Object value, String className) throws SQLException {
        Class<T> clazz = null;
        try {
            clazz = (Class<T>) Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return selectOne(sql, value, clazz);
    }

    public <T> T selectOne(String sql, Object value, Class<T> resultClazz) throws SQLException {
        List<T> results = selectList(sql, value, resultClazz);

        if (results.size() == 1) {
            return results.get(0);
        }
        else if (results.size() > 1) {
            throw new SQLWarning();
        }
        else {
            return null;
        }
    }

    public <T> List<T> selectList(String sql, String className) throws SQLException {
        return selectList(sql, null, className);
    }

    public <T> List<T> selectList(String sql, Class<T> resultClazz) throws SQLException {
        return selectList(sql, null, resultClazz);
    }

    public <T> List<T> selectList(String sql, Object value, String className) throws SQLException {
        Class<T> clazz = null;
        try {
            clazz = (Class<T>) Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return selectList(sql, value, clazz);
    }

    public <T> List<T> selectList(String sql, Object value, Class<T> resultClazz) throws SQLException {
        BoundSql boundSql = new BoundSql(sql, value, resultClazz);
        return executor.query(boundSql);
    }

    public int update(String sql) throws SQLException {
        return update(sql, null);
    }

    public int update(String sql, Object parameter) throws SQLException {
    	BoundSql boundSql = new BoundSql(sql, parameter, null);
        return executor.update(boundSql);
    }

    public int insert(String sql) throws SQLException {
        return insert(sql, null);
    }
    public int insert(String sql, Object parameter) throws SQLException {
        return update(sql, parameter);
    }
    public int delete(String sql) throws SQLException {
        return delete(sql, null);
    }
    public int delete(String sql, Object parameter) throws SQLException {
        return update(sql, parameter);
    }

}
