package com.f3.ioc;

import java.util.ArrayList;

public class PropertyValues {

	private ArrayList<PropertyValue> properties=new ArrayList<PropertyValue>();
	
	public void add(PropertyValue property){
		properties.add(property);
	}
}
