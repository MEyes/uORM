package com.f3.ioc;

import java.io.InputStream;
import java.util.HashMap;

public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader{

	private HashMap<String, BeanDefinition> beanDefinitionMap;
	private ResourceLoader resourceLoader;
	public AbstractBeanDefinitionReader(ResourceLoader resourceLoader) {
		beanDefinitionMap=new HashMap<String,BeanDefinition>();
		this.resourceLoader=resourceLoader;
	}	
	
	public HashMap<String, BeanDefinition> getBeanDefinitionMap(){
		return this.beanDefinitionMap;
	}
	
	public ResourceLoader getResourceLoader(){
		return resourceLoader;
	}

}
