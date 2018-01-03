package com.f3.ioc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public abstract class AbstractBeanFactory implements BeanFactory {

	private HashMap<String, BeanDefinition> beanDefinitionMap = new HashMap<String, BeanDefinition>();

	private final List<String> beanDefinitionNames=new ArrayList<String>();
	@Override
	public Object getBean(String name) throws Exception{
		BeanDefinition beanDefinition=beanDefinitionMap.get(name);
		if (beanDefinition==null) {
			throw new IllegalArgumentException();
		}
		Object bean=beanDefinition.getBean();
		if (bean==null) {
			bean=doCreateBean(beanDefinition);
		}
		return bean;
	}

	@Override
	public void registerBeanDefinition(String name, BeanDefinition beanDefinition) throws Exception{

		beanDefinitionMap.put(name, beanDefinition);
		beanDefinitionNames.add(name);
	}
	
	public void preInstantiateSingletons() throws Exception{
		for (Iterator iterator = beanDefinitionNames.iterator(); iterator.hasNext();) {
			String beanName = (String) iterator.next();
			getBean(beanName);
		}
	}

	protected abstract Object doCreateBean(BeanDefinition beanDefinition) throws Exception;

}
