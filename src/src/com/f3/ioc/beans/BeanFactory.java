package com.f3.ioc.beans;

public interface BeanFactory {
	Object getBean(String name) throws Exception;
}
