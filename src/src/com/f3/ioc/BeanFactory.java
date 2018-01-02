package com.f3.ioc;

import java.util.HashMap;


public class BeanFactory {

	private  HashMap<String, Object> map=new HashMap<String,Object>();
	
	public Object getBean(String name){
		return map.get(name);
	}
	
	public void register(String key,String bean) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		
		Class clazz = Class.forName(bean);
		Object newInstance = clazz.newInstance();
		
		map.put(key,newInstance);
	}
	
	
	
	public static void main(String[] args)  throws Exception{
		BeanFactory beanFactory=new BeanFactory();
		beanFactory.register("hello", "com.f3.ioc.HelloWorldService");
		
		HelloWorldService bean = (HelloWorldService)beanFactory.getBean("hello");
		bean.sayHello();
		
	}
}

class HelloWorldService{
	
	public void sayHello(){
		System.out.println("Hello world!");
	}
}