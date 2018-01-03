package com.f3.ioc;

public interface BeanFactory {
	Object getBean(String name) throws Exception;
	void registerBeanDefinition(String name,BeanDefinition beanDefinition) throws Exception;
}
