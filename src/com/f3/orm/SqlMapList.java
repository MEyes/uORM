package com.f3.orm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.f3.orm.mapping.MappedStatement;

public class SqlMapList {
	
	public static final Map<String, MappedStatement> maps=new HashMap<String,MappedStatement>();
	public static final List<MappedStatement> statements=new ArrayList<MappedStatement>();
	public static void addStatement(MappedStatement statement){
		if (statements.contains(statement)) {
			return ;
		}
		statements.add(statement);
		maps.put(statement.getId(),statement);
	}
}
