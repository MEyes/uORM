package com.f3.ioc;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class Startup {
	public static void main(String[] args)  throws Exception{
		
//		InputStream inputStream = Startup.class.getClassLoader().getResourceAsStream("main.xml");
//		BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
//		String content;
//		while((content=bufferedReader.readLine())!=null){
//			System.out.println(content);
//		}
		
		BeanFactory beanFactory =new AutowireCapableBeanFactory();
		XmlBeanDefinitionReader xmlBeanDefinitionReader=new XmlBeanDefinitionReader(new ResourceLoader());
		xmlBeanDefinitionReader.loadBeanDefinition("main.xml");
		HashMap<String,BeanDefinition> beanDefinitionMap = xmlBeanDefinitionReader.getBeanDefinitionMap();
		for (Entry<String, BeanDefinition> entry : beanDefinitionMap.entrySet()) {
			beanFactory.registerBeanDefinition(entry.getKey(), entry.getValue());
			System.out.println(entry.getKey());
		}
/*		BeanDefinition beanDefinition=new BeanDefinition();
		beanDefinition.setBeanClassName("com.f3.ioc.HelloWorldService");
		
		PropertyValues propertyValues=new PropertyValues();
		propertyValues.add(new PropertyValue("content", "你好"));
		
		beanDefinition.setPropertyValues(propertyValues);
		
		beanFactory.registerBeanDefinition("hello", beanDefinition);*/
		
		HelloWorldService bean = (HelloWorldService)beanFactory.getBean("hello");
		bean.sayHello();
		
	}
}

class HelloWorldService{
	
	public String content;
	public void sayHello(){
		System.out.println("Hello world!"+content);
	}
}
