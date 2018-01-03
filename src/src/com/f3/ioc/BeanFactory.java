package com.f3.ioc;

public interface BeanFactory {
	Object getBean(String name);
	void registerBeanDefinition(String name,BeanDefinition beanDefinition) throws Exception;
}
