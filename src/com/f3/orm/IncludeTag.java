package com.f3.orm;

public class IncludeTag extends AbstractTag{
	
	private String refId;
	
	public String getRefId() {
		return refId;
	}
	public void setRefId(String refId) {
		this.refId = refId;
	}
	private Statement statement;
	
	public Statement getStatement() {
		return statement;
	}
	public void setStatement(Statement statement) {
		this.statement = statement;
	}
	@Override
	public String buildSql(Context context) {
		String sql=statement.buildSql(context);
		//prepend+sql
		return sql;
	}
	@Override
	public TagType getType() {
		return TagType.Include;
	}
}
