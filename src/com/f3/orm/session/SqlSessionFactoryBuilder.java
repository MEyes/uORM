package com.f3.orm.session;

import java.io.InputStream;

public class SqlSessionFactoryBuilder {
	
	public SqlSessionFactory build(InputStream imputStream){
		
		XMLConfigBuilder xmlConfigBuilder=new XMLConfigBuilder(imputStream);
		return new SqlSessionFactory(xmlConfigBuilder.parse());
	}
}
