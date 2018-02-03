package com.f3.dynamicsql;

import java.util.Map;

public class Context {
	String sqlId;
	Map<String,Object> prameters;
	public String getSqlId() {
		return sqlId;
	}
	public void setSqlId(String sqlId) {
		this.sqlId = sqlId;
	}
	public Map<String, Object> getPrameters() {
		return prameters;
	}
	public void setPrameters(Map<String, Object> prameters) {
		this.prameters = prameters;
	}

}
