package com.f3.example;


public class HelloWorldServiceImpl implements HelloWorldService {
	public String content;
	private Tool tool;
	
	public void sayHello(){
		System.out.println("Hello world!"+content);
	}
}
