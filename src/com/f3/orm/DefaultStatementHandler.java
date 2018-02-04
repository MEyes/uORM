package com.f3.orm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class DefaultStatementHandler implements StatementHandler {

    private PreparedStatement statement;

    public DefaultStatementHandler(PreparedStatement statement) {
        this.statement = statement;
    }

    public void setStatement(PreparedStatement statement) {
        this.statement = statement;
    }

    public ResultSet doQuery(BoundSql boundSql) throws SQLException {
    	boundSql.updatePrepareStatement(statement);
        return statement.executeQuery();
    }

    public int doUpdate(BoundSql boundSql) throws SQLException {
    	boundSql.updatePrepareStatement(statement);
        return statement.executeUpdate();
    }

}