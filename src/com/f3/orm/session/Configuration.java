package com.f3.orm.session;

import com.f3.orm.binding.MapperRegistry;
import com.f3.orm.type.TypeHandlerRegistry;

public class Configuration {
	public final MapperRegistry mapperRegistry=new MapperRegistry(this);
	public final TypeHandlerRegistry typeHandlerRegistry = new TypeHandlerRegistry();
}
