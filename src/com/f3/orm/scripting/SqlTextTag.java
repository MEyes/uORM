package com.f3.orm.scripting;

import com.f3.orm.Context;

public class SqlTextTag extends AbstractTag{

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
	public TagType getType() {
		return TagType.SqlText;
	}
}