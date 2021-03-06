package com.f3.orm.executor;

import java.sql.SQLException;
import java.util.List;

import com.f3.orm.mapping.BoundSql;

public interface Executor {
    boolean isAutoCommit();

    void setAutoCommit(boolean autoCommit);

    <T> List<T> query(BoundSql sql) throws SQLException;

    int update(BoundSql sql) throws SQLException;
}
