package com.f3.orm.scripting;

import java.util.Map;

public class TextSqlNode extends AbstractSqlNode{

	String bodyText;
	public String getBodyText() {
		return bodyText;
	}
	public void setBodyText(String bodyText) {
		this.bodyText = bodyText;
	}
	
	@Override
	public String buildSql(Map<String,Object> prameters) {
		return bodyText;
	}
	@Override
	public SqlNodeType getType() {
		return SqlNodeType.SqlText;
	}
}
