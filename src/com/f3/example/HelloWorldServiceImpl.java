package com.f3.example;

import com.f3.ioc.beans.BeanPostProcessor;

public class HelloWorldServiceImpl implements HelloWorldService {
	public String content;
//	private Tool tool;
	
	public void sayHello(){
		System.out.println("Hello world!"+content);
	}

	@Override
	public int sayHi(String name, int value) {
		System.out.println("hi");
		return 1;
	}

	public Object postProcessBeforeInitialization(Object bean, String beanName) throws Exception {
		System.out.println("Before---Initialize bean " + beanName + " start!");
		return bean;
	}

	public Object postProcessAfterInitialization(Object bean, String beanName) throws Exception {
		System.out.println("After---Initialize bean " + beanName + " start!");
		return bean;
	}
}
