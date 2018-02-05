package com.f3.orm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SqlMapList {
	
	public static final Map<String, Statement> maps=new HashMap<String,Statement>();
	public static final List<Statement> statements=new ArrayList<Statement>();
	public static void addStatement(Statement statement){
		if (statements.contains(statement)) {
			return ;
		}
		statements.add(statement);
		maps.put(statement.getId(),statement);
	}
}
