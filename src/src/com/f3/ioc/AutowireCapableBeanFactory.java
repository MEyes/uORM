package com.f3.ioc;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;


public class AutowireCapableBeanFactory extends AbstractBeanFactory {

	@Override
	protected Object doCreateBean(BeanDefinition beanDefinition) {
		try {
			Object bean = beanDefinition.getBeanClass().newInstance();
			try {
				List<PropertyValue> propertyValues = beanDefinition.getPropertyValues().getPropertyValues();

				for (PropertyValue propertyValue : propertyValues) {
						
					Field field;
					try {
						field = bean.getClass().getDeclaredField(propertyValue.getName());
						field.setAccessible(true);
						field.set(bean, propertyValue.getValue());
					} catch (NoSuchFieldException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return bean;
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
}
