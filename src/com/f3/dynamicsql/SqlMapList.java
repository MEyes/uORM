package com.f3.dynamicsql;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SqlMapList {
	
	static final Map<String, Statement> maps=new HashMap<String,Statement>();
	
	static final List<Statement> statements=new ArrayList<Statement>();

	public static void addStatement(Statement statement){
		if (statements.contains(statement)) {
			return ;
		}
		statements.add(statement);
		//ÁÙÊ±
		maps.put(statement.getId(),statement);
	}
}
