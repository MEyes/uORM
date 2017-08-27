package com.f3.core;

public class OrderByClause {

	private String field;
	private OrderByType orderByType;
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public OrderByType getOrderByType() {
		return orderByType;
	}
	public void setOrderByType(OrderByType orderByType) {
		this.orderByType = orderByType;
	}
	@Override
	public String toString() {

		return toString(false, "[", "]");
	}
	
	public String toString(Boolean surround,String left,String right){
		
		
		return surround?left+field+right+" "+orderByType.toString():field+" "+orderByType.toString();
		
		
	}
	
	
}
