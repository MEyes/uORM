package com.f3.ioc.beans;

import java.util.ArrayList;
import java.util.List;

public class PropertyValues {

	private List<PropertyValue> propertyValues=new ArrayList<PropertyValue>();
	
	public void addPropertyValue(PropertyValue propertyValue){
		propertyValues.add(propertyValue);
	}
	
	public List<PropertyValue> getPropertyValues(){
		return propertyValues;
	}
}
