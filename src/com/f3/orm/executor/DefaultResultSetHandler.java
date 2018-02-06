package com.f3.orm.executor;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.f3.orm.mapping.BoundSql;
import com.f3.orm.type.ColumnTypeHandler;

public class DefaultResultSetHandler implements ResultSetHandler {

	private ResultSet resultSet;
	
	public DefaultResultSetHandler(ResultSet resultSet,BoundSql boundSql) {
		this.resultSet=resultSet;
		this.columnMap=new LinkedHashMap<String,Integer>();
		this.boundSql=boundSql;
		
		try {
			ResultSetMetaData metaData= resultSet.getMetaData();
			int coumn=metaData.getColumnCount();
			
			for(int i=1;i<=coumn;i++){
				String columnName=metaData.getColumnName(i);
				int columnType=metaData.getColumnType(i);
				
				this.columnMap.put(columnName, columnType);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
	}
	private Map<String,Integer> columnMap;
	private BoundSql boundSql;
	
	@Override
	public <T> List<T> handleResultSet() throws SQLException {
		List<T> list=new ArrayList<>();
		Class resultClazz=this.boundSql.getResultClazz();
		
		while (this.resultSet.next()) {
			T result=null;
			try {
				 Constructor<T> declaredConstructor = resultClazz.getDeclaredConstructor();
				 result=declaredConstructor.newInstance();
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			for (Map.Entry<String, Integer> entry : this.columnMap.entrySet()) {
				String columnName=entry.getKey();
				int columnType=entry.getValue();
				
				Object value=ColumnTypeHandler.getResult(resultSet, columnName, columnType);
				handleResultSetValue(result, columnName, value);
			}
			list.add(result);
			
		}
		return list;
	}
	
	private <T> void handleResultSetValue(T object,String name,Object value){
		Class<?> clazz = object.getClass();
		Field[] fields = clazz.getDeclaredFields();
		
		for (Field field : fields) {
			
			String fieldName=field.getName();
			if (name.equals(fieldName)) {
				try {
					PropertyDescriptor descriptor=new PropertyDescriptor(fieldName,clazz);
					Method method=descriptor.getWriteMethod();
					method.invoke(object, value);
					break;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
			
		}
	}

	@Override
	public <E> List<E> handleResultSets(Statement stmt) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
