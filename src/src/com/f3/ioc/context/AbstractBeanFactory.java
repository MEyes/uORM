package com.f3.ioc.context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.f3.aop.AspectJExpressionPointcutAdvisor;
import com.f3.ioc.beans.BeanDefinition;
import com.f3.ioc.beans.BeanFactory;
import com.f3.ioc.beans.BeanPostProcessor;

public abstract class AbstractBeanFactory implements BeanFactory {

	private HashMap<String, BeanDefinition> beanDefinitionMap = new HashMap<String, BeanDefinition>();

	private final List<String> beanDefinitionNames=new ArrayList<String>();
	private List<BeanPostProcessor> beanPostProcessors = new ArrayList<BeanPostProcessor>();
	
	@Override
	public Object getBean(String name) throws Exception{
		BeanDefinition beanDefinition=beanDefinitionMap.get(name);
		if (beanDefinition==null) {
			throw new IllegalArgumentException();
		}
		Object bean=beanDefinition.getBean();
		if (bean==null) {
			bean = doCreateBean(beanDefinition);
            bean = initializeBean(bean, name);
		}
		return bean;
	}
	protected Object initializeBean(Object bean, String name) throws Exception {
		for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
			bean = beanPostProcessor.postProcessBeforeInitialization(bean, name);
		}

		// TODO:call initialize method
		for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
            bean = beanPostProcessor.postProcessAfterInitialization(bean, name);
		}
        return bean;
	}
	protected Object createBeanInstance(BeanDefinition beanDefinition) throws Exception {
		return beanDefinition.getBeanClass().newInstance();
	}
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
	
	protected Object doCreateBean(BeanDefinition beanDefinition) throws Exception {
		Object bean = createBeanInstance(beanDefinition);
		beanDefinition.setBean(bean);
		applyPropertyValues(bean, beanDefinition);
		return bean;
	}

	protected void applyPropertyValues(Object bean, BeanDefinition beanDefinition) throws Exception {

	}

	public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) throws Exception {
		this.beanPostProcessors.add(beanPostProcessor);
	}

	public List getBeansForType(Class type) throws Exception {
		List beans = new ArrayList<Object>();
		for (String beanDefinitionName : beanDefinitionNames) {
			if (type.isAssignableFrom(beanDefinitionMap.get(beanDefinitionName).getBeanClass())) {
				beans.add(getBean(beanDefinitionName));
			}
		}
		return beans;
	}
}
