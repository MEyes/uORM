package com.f3.orm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.f3.orm.mapping.BoundSql;

public interface StatementHandler {

    void setStatement(PreparedStatement statement);

    ResultSet doQuery(BoundSql wrapSql) throws SQLException;

    int doUpdate(BoundSql wrapSql) throws SQLException;

}
