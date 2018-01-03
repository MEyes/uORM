package com.f3.ioc;

import java.util.HashMap;

public abstract class AbstractBeanFactory implements BeanFactory {

	private HashMap<String, BeanDefinition> beanDefinitionMap = new HashMap<String, BeanDefinition>();

	@Override
	public Object getBean(String name) {
		return beanDefinitionMap.get(name).getBean();
	}

	@Override
	public void registerBeanDefinition(String name, BeanDefinition beanDefinition) throws Exception{

		Object bean=doCreateBean(beanDefinition);
		beanDefinition.setBean(bean);
		beanDefinitionMap.put(name, beanDefinition);
	}

	protected abstract Object doCreateBean(BeanDefinition beanDefinition) throws Exception;

}
