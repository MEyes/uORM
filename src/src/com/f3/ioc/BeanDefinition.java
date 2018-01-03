package com.f3.ioc;

public class BeanDefinition {

	private Object bean;
	private Class beanClass;
	private String beanClassName;
	
	private PropertyValues propertyValues;
	
	public Object getBean() {
		return bean;
	}
	public void setBean(Object bean) {
		this.bean = bean;
	}
	public Class getBeanClass() {
		return beanClass;
	}
	public void setBeanClass(Class beanClass)  {
		this.beanClass = beanClass;
		
		try {
			Object bean = this.beanClass.newInstance();
			setBean(bean);
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getBeanClassName() {
		return beanClassName;
	}
	public void setBeanClassName(String beanClassName) {
		this.beanClassName = beanClassName;
		try {
			Class beanClass=Class.forName(beanClassName);
			setBeanClass(beanClass);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
}
