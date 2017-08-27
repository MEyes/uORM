package com.f3.core;

import java.util.ArrayList;
import java.util.List;

public class CriteriaData {

	private String from;
	private List<SelectField> selectFields;
	private List<Condition> conditions;
	private List<OrderByClause> orderByClauses;
	private int limit;
	
	public CriteriaData(){
		
		conditions=new ArrayList<Condition>();
		
		
	}
	
	
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public List<SelectField> getSelectFields() {
		return selectFields;
	}
	public void setSelectFields(List<SelectField> selectFields) {
		this.selectFields = selectFields;
	}
	public List<Condition> getConditions() {
		return conditions;
	}
	public void setConditions(List<Condition> conditions) {
		this.conditions = conditions;
	}
	public List<OrderByClause> getOrderByClauses() {
		return orderByClauses;
	}
	public void setOrderByClauses(List<OrderByClause> orderByClauses) {
		this.orderByClauses = orderByClauses;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	
	
	
}
