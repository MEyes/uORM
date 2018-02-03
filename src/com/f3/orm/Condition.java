package com.f3.orm;

public class Condition {

	private ConditionType conditionType;
	private String field;
	private String comparison;
	private String value;
	
	public Condition(){
		
	}
	
	public Condition(ConditionType conditionType,String field){
		this.conditionType=conditionType;
	    this.field=field;
	}

	public ConditionType getConditionType() {
		return conditionType;
	}

	public void setConditionType(ConditionType conditionType) {
		this.conditionType = conditionType;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getComparison() {
		return comparison;
	}

	public void setComparison(String comparison) {
		this.comparison = comparison;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		
		return toString(false, "[", "]");
	}
	
	public String toString(Boolean surround,String left,String right){
		
		String col=surround?left+field+right:field;
		String val=(value!=null && !value.equals(""))?value:"''";
		
		return String.format("{0} {1} {2}", col,comparison,val);
	}
	
	
}
