package com.f3.orm.mapping;

import java.util.Map;

public class SqlBuilder {
	 public String build(Map<String, Object> parameters, MappedStatement statement)
     {
         if (statement == null)
         {
        	 throw new RuntimeException();
         }
         String sql = statement.buildSql(parameters);
         return sql;
     }
}
