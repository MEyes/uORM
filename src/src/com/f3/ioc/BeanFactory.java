package com.f3.ioc;

import java.util.HashMap;


public class BeanFactory {

	private  HashMap<String, BeanDefinition> map=new HashMap<String,BeanDefinition>();
	
	public BeanDefinition getBeanDefinition(String name){
		return map.get(name);
	}
	
	public void register(String key,String beanClassName) {
				
		BeanDefinition beanDefinition = new BeanDefinition();
		beanDefinition.setBeanClassName(beanClassName);
		map.put(key, beanDefinition);
	}
	
	
	
	public static void main(String[] args) {
		BeanFactory beanFactory=new BeanFactory();
		BeanDefinition beanDefinition=new BeanDefinition();
	
		beanFactory.register("hello", "com.f3.ioc.HelloWorldService");
		
		HelloWorldService bean = (HelloWorldService)beanFactory.getBeanDefinition("hello").getBean();
		bean.sayHello();
		
	}
}

class HelloWorldService{
	
	public String content;
	public void sayHello(){
		System.out.println("Hello world!"+content);
	}
}