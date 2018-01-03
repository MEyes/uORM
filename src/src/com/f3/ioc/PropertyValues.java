package com.f3.ioc;

import java.util.ArrayList;
import java.util.List;

public class PropertyValues {

	private List<PropertyValue> propertyValues=new ArrayList<PropertyValue>();
	
	public void add(PropertyValue propertyValue){
		propertyValues.add(propertyValue);
	}
	
	public List<PropertyValue> getPropertyValues(){
		return propertyValues;
	}
}
