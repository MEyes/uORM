package com.f3.orm;

import java.util.Map;

public class Context {
	String sqlId;
	public Map<String,Object> prameters;
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
