package com.f3.orm.scripting;

import com.f3.orm.Context;

public class TextSqlNode extends AbstractSqlNode{

	String bodyText;
	public String getBodyText() {
		return bodyText;
	}
	public void setBodyText(String bodyText) {
		this.bodyText = bodyText;
	}
	
	@Override
	public String buildSql(Context context) {
		return bodyText;
	}
	@Override
	public SqlNodeType getType() {
		return SqlNodeType.SqlText;
	}
}
