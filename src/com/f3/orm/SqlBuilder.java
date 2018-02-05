package com.f3.orm;

import com.f3.orm.mapping.MappedStatement;

public class SqlBuilder {
	 public String buildSql(Context context, MappedStatement statement)
     {
         if (statement == null)
         {
        	 throw new RuntimeException();
         }
         String sql = statement.buildSql(context);
         return sql;
     }
}
