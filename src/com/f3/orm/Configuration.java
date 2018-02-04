package com.f3.orm;


public class Configuration {
	public final MapperRegistry mapperRegistry=new MapperRegistry(this);
	public final TypeHandlerRegistry typeHandlerRegistry = new TypeHandlerRegistry();
}
