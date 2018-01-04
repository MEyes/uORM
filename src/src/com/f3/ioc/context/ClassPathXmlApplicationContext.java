package com.f3.ioc.context;

import java.util.Map.Entry;

import com.f3.ioc.beans.BeanDefinition;
import com.f3.ioc.beans.XmlBeanDefinitionReader;
import com.f3.ioc.io.ResourceLoader;

public class ClassPathXmlApplicationContext extends AbstractApplicationContext{

	private String configLocation;
	public ClassPathXmlApplicationContext(String configLocation) throws Exception {
		this(configLocation, new AutowireCapableBeanFactory());
	}

	public ClassPathXmlApplicationContext(String configLocation, AbstractBeanFactory beanFactory) throws Exception {
		super(beanFactory);
		this.configLocation = configLocation;
		refresh();
	}

	@Override
	public void refresh() throws Exception {
		XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new ResourceLoader());
		xmlBeanDefinitionReader.loadBeanDefinition(configLocation);
		for (Entry<String, BeanDefinition> beanDefinitionEntry : xmlBeanDefinitionReader.getBeanDefinitionMap().entrySet()) {
			beanFactory.registerBeanDefinition(beanDefinitionEntry.getKey(), beanDefinitionEntry.getValue());
		}
	}

}
