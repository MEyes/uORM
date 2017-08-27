package com.f3.core;

import org.apache.tomcat.util.codec.binary.StringUtils;

public class SelectField {

	private String field;
	private String alias;
	
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	@Override
	public String toString() {
		
		return toString(false, "[", "]");
	}
	
	public String toString(Boolean surround,String left,String right){
		
		String aliasText=(alias!=null && !alias.equals(""))?" as "+field:"";
		
		return surround?left+field+right+aliasText:field+aliasText;
		
		
	}
	
}
