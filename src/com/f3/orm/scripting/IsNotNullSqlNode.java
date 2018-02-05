package com.f3.orm.scripting;

import java.util.Map;

public class IsNotNullSqlNode extends AbstractSqlNode{


	@Override
	public SqlNodeType getType() {
		return SqlNodeType.IsNotEmpty;
	}
	@Override
	public boolean test(Map<String, Object> parameters) {
		Object propertyValue = getPropertyValue(parameters);
		
		if (propertyValue==null || propertyValue.equals("")) {
			return false;
		}
		
		return true;
	}
}
