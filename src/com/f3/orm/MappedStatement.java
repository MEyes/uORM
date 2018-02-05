package com.f3.orm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MappedStatement {
	
static final Map<Class, Statement> maps=new HashMap<Class,Statement>();
	
	static final List<Statement> statements=new ArrayList<Statement>();

	public static void addStatement(Class clazz,Statement statement){
		if (statements.contains(statement)) {
			return ;
		}
		statements.add(statement);
		maps.put(clazz,statement);
	}
}
