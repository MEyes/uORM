package com.f3.ioc;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;


public class AutowireCapableBeanFactory extends AbstractBeanFactory {

	@Override
	protected Object doCreateBean(BeanDefinition beanDefinition)  throws Exception{
		Object bean=createBeanInstance(beanDefinition);
		applyPropertyValues(bean, beanDefinition);
		return bean;
	}
	
	
	protected Object createBeanInstance(BeanDefinition beanDefinition) throws Exception{
		return beanDefinition.getBeanClass().newInstance();
	}
	protected void applyPropertyValues(Object bean, BeanDefinition beanDefinition) throws Exception{
		for (PropertyValue propertyValue : beanDefinition.getPropertyValues().getPropertyValues()) {
			Field field = bean.getClass().getDeclaredField(propertyValue.getName());
			field.setAccessible(true);
			field.set(bean, propertyValue.getValue());
		}
	}
}
