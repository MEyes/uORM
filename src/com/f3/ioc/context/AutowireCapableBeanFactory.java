package com.f3.ioc.context;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;

import com.f3.ioc.beans.BeanDefinition;
import com.f3.ioc.beans.BeanFactoryAware;
import com.f3.ioc.beans.BeanReference;
import com.f3.ioc.beans.PropertyValue;



public class AutowireCapableBeanFactory extends AbstractBeanFactory {

//	@Override
//	protected Object doCreateBean(BeanDefinition beanDefinition)  throws Exception{
//	
//		Object bean=createBeanInstance(beanDefinition);
//		beanDefinition.setBean(bean);
//		applyPropertyValues(bean, beanDefinition);
//		return bean;
//	}
//	
//	
//	protected Object createBeanInstance(BeanDefinition beanDefinition) throws Exception{
//		return beanDefinition.getBeanClass().newInstance();
//	}
//	protected void applyPropertyValues(Object bean, BeanDefinition beanDefinition) throws Exception{
//		if (bean instanceof BeanFactoryAware) {
//			((BeanFactoryAware) bean).setBeanFactory(this);
//		}
//		for (PropertyValue propertyValue : beanDefinition.getPropertyValues().getPropertyValues()) {
//			Field field = bean.getClass().getDeclaredField(propertyValue.getName());
//			field.setAccessible(true);
//			Object value= propertyValue.getValue();
//			if (value instanceof BeanReference) {
//				BeanReference beanReference=(BeanReference)value;
//				value=getBean(beanReference.getName());
//			}
//			field.set(bean,value);
//		}
//	}
	protected void applyPropertyValues(Object bean, BeanDefinition mbd) throws Exception {
		if (bean instanceof BeanFactoryAware) {
			((BeanFactoryAware) bean).setBeanFactory(this);
		}
		for (PropertyValue propertyValue : mbd.getPropertyValues().getPropertyValues()) {
			Object value = propertyValue.getValue();
			if (value instanceof BeanReference) {
				BeanReference beanReference = (BeanReference) value;
				value = getBean(beanReference.getName());
			}

			try {
				Method declaredMethod = bean.getClass().getDeclaredMethod(
						"set" + propertyValue.getName().substring(0, 1).toUpperCase()
								+ propertyValue.getName().substring(1), value.getClass());
				declaredMethod.setAccessible(true);

				declaredMethod.invoke(bean, value);
			} catch (NoSuchMethodException e) {
				Field declaredField = bean.getClass().getDeclaredField(propertyValue.getName());
				declaredField.setAccessible(true);
				declaredField.set(bean, value);
			}
		}
	}
	
}
