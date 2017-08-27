package com.f3.core;

import com.sun.xml.internal.ws.handler.HandlerProcessor.RequestOrResponse;

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
	
	private void end(){
		
		
	}
}
