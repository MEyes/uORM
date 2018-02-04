package com.f3.orm;

import java.sql.SQLException;
import java.util.List;

public interface Executor {
    boolean isAutoCommit();

    void setAutoCommit(boolean autoCommit);

    <T> List<T> query(BoundSql sql) throws SQLException;

    int update(BoundSql sql) throws SQLException;
}
