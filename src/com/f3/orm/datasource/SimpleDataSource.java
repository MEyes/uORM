package com.f3.orm.datasource;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.logging.Logger;

import javax.sql.DataSource;


public class SimpleDataSource implements DataSource {
	
	private static LinkedList<Connection> pool = new LinkedList<Connection>();
	static{
		try {
			for (int i = 0; i < 10; i++) {
				Connection conn = DBUtils.getConnection();
				Connection wrapConn=new SimpleConnection(conn, pool);
				pool.add(wrapConn);
			}
		} catch (Exception e) {
			throw new ExceptionInInitializerError("初始化数据库连接失败，请检查配置文件是否正确！");
		}
	}
	@Override
	public Connection getConnection() throws SQLException {
		
		Connection conn = null;
		if(pool.size()>0){
			conn =  pool.removeFirst();//从池中取出一个连接
		}else{
			
			try {
				conn=DBUtils.getConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return conn;
	}
	
	@Override
	public Connection getConnection(String username, String password) throws SQLException {
		
			return null;
		
	}
	@Override
	public PrintWriter getLogWriter() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setLoginTimeout(int seconds) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getLoginTimeout() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}




}
