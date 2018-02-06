package com.f3.orm.executor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.f3.orm.mapping.BoundSql;
public class DefaultStatementHandler implements StatementHandler {

    private PreparedStatement statement;

    public DefaultStatementHandler(PreparedStatement statement) {
        this.statement = statement;
    }

    public void setStatement(PreparedStatement statement) {
        this.statement = statement;
    }

    public ResultSet doQuery(BoundSql boundSql) throws SQLException {
        return statement.executeQuery();
    }

    public int doUpdate(BoundSql boundSql) throws SQLException {
        return statement.executeUpdate();
    }

}
