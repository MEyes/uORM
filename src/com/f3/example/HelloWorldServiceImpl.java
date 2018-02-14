package com.f3.example;

import java.util.Map;

public class HelloWorldServiceImpl implements HelloWorldService {
	public String content;
//	private Tool tool;
	
	public Map<String,String> map;
	
	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}

	public void sayHello(){
		System.out.println("Hello world!"+map);
	}

	@Override
	public int sayHi(String name, int value) {
		System.out.println(name);
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
