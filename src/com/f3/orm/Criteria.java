package com.f3.orm;

import java.text.DateFormat;
import java.util.Date;

import org.w3c.dom.ls.LSException;

public class Criteria {

	private CriteriaData criteriaData;
	
	
	
	private Criteria(){
		
	}
	public static Criteria Create(){
		return new Criteria();
	}
	
	public Criteria addCondition(String field,ComparisonType comparisonType,Object value){
		
		
		return this;
	}
	
	public Criteria select(String[] cols){
		
		return this;
	}
	
	public Criteria where(String field){
		
		return this;
	}
	
	public Criteria and(String field){
		return this;
	}
	
	public Criteria or(String field){
		return this;
	}
	
	public Criteria orderBy(String field){
		
		return this;
	}
	
	public Criteria orderByDescending(String field){
		
		return this;
	}
	
	public Criteria as(String columnAlias){
		
		return this;
	}
	
	public Criteria from(String tableName){
		
		return this;
	}
	
	public Criteria is(Object value){
		
		return this;
	}
	
	public Criteria not(Object value){
		
		return this;
	}
	
	public Criteria isNull(){
		
		return this;
	}
	
	public Criteria notNull(){
		
		return this;
	}
	
	public Criteria in(Object[] values){
		return this;
	}
	
	public Criteria notIn(Object[] values){
		return this;
	}
	
	public Criteria like(String values){
		
		return this;
	}
	
	public Criteria moreThan(Object value){
		return this;
	}
	
	public Criteria moreEqual(Object value){
		return this;
	}
	public Criteria lessThan(Object value){
		return this;
	}
	
	public Criteria lessEqual(Object value){
		return this;
	}
	
	public Criteria limit(int limit){
		
		return this;
	}
	
	public void end(){
		
		
	}
	
	
	private Condition currentCondition;
	
	public Criteria startNewCondition(ConditionType conditionType,String field){
		
		if (currentCondition!=null) {
			
			criteriaData.getConditions().add(currentCondition);
			
		}
		currentCondition=new Condition(conditionType,field);
	
		return this;
		
	}
	
	public String convertValue(Object value){
		
		String valueToUse="";
		if (value==null || (value instanceof String && String.valueOf(value).equals(""))) {
			
			return "''";
		}
		
		
		else if (value instanceof String) {
			
			valueToUse="'"+encode(String.valueOf(value))+"'";
		}	
		
		else if (value instanceof Boolean) {
			
			Boolean bValue=(Boolean)value;
			valueToUse=bValue?"1":"0";
		}
		else if (value instanceof Date){
			
			Date dValue=(Date)value;
			valueToUse="'"+dValue.toLocaleString()+"'";
		}
		
		//TODO:Numeric
		return valueToUse;
		
		
	}
	
	public void BuildCondition(){
		
	}
	
	private String encode(String value){
		
		if (value!=null && !value.equals("")) {
			
			
			return value.replace("'", "''");
		}
		return value;
	}
}
