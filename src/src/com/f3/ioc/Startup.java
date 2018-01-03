package com.f3.ioc;

import java.util.ArrayList;

public class Startup {
	public static void main(String[] args) {
		
		BeanFactory beanFactory =new AutowireCapableBeanFactory();
		BeanDefinition beanDefinition=new BeanDefinition();
		beanDefinition.setBeanClassName("com.f3.ioc.HelloWorldService");
		beanDefinition.setPropertyValues(new PropertyValues());
		beanDefinition.getPropertyValues().add(new PropertyValue("content", "你好"));
		
		beanFactory.registerBeanDefinition("hello", beanDefinition);
		
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
