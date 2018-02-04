package com.f3.orm;

public class SqlBuilder {
	 public String buildSql(Context context, Statement statement)
     {
         if (statement == null)
         {
        	 throw new RuntimeException();
         }
         String sql = statement.buildSql(context);
         return sql;
     }
}
