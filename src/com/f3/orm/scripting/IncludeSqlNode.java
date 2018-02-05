package com.f3.orm.scripting;

import com.f3.orm.Context;
import com.f3.orm.mapping.MappedStatement;

public class IncludeSqlNode extends AbstractSqlNode{
	
	private String refId;
	
	public String getRefId() {
		return refId;
	}
	public void setRefId(String refId) {
		this.refId = refId;
	}
	private MappedStatement statement;
	
	public MappedStatement getStatement() {
		return statement;
	}
	public void setStatement(MappedStatement statement) {
		this.statement = statement;
	}
	@Override
	public String buildSql(Context context) {
		String sql=statement.buildSql(context);
		//prepend+sql
		return sql;
	}
	@Override
	public SqlNodeType getType() {
		return SqlNodeType.Include;
	}
}
