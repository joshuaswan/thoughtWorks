package harry.tan.dao;

import harry.tan.utils.HomeWorkConstant;
import harry.tan.utils.RequestUtil;

import java.io.PrintWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.LinkedList;
import java.util.Properties;
import java.util.logging.Logger;

import javax.sql.DataSource;

public class Pool implements DataSource{
	
	private static LinkedList<Connection> connections  = new LinkedList<Connection>();

	static
	{
		try 
		{
			Properties p = RequestUtil.getDbParam();
			String DBDrive = p.getProperty("driver",HomeWorkConstant.EMPTY);
			String DBUrl = p.getProperty("url",HomeWorkConstant.EMPTY);
			String DBUser = p.getProperty("username",HomeWorkConstant.EMPTY);
			String DBPassword = p.getProperty("password",HomeWorkConstant.EMPTY);
			Integer initSize = Integer.valueOf(p.getProperty("initSize",HomeWorkConstant.EMPTY));
			Class.forName(DBDrive);
			for(int i=0;i<initSize;i++){
				Connection conn = DriverManager.getConnection(DBUrl, DBUser, DBPassword);
				connections.add(conn);
			}
		}
		catch (ClassNotFoundException e) {
			System.err.println(e);
		}
		catch (SQLException e) {
			System.err.println(e);
		}
	}

	@Override
	public PrintWriter getLogWriter() throws SQLException {
		return null;
	}

	@Override
	public int getLoginTimeout() throws SQLException {
		return 0;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		return null;
	}

	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {
		
	}

	@Override
	public void setLoginTimeout(int seconds) throws SQLException {
		
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		return null;
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return false;
	}

	@Override
	public Connection getConnection() throws SQLException {
		if(connections.size() > 0){
			final Connection conn = connections.removeFirst();
			return (Connection)Proxy.newProxyInstance(Pool.class.getClassLoader(), Connection.class.getInterfaces(), new InvocationHandler(){

				@Override
				public Object invoke(Object proxy, Method method, Object[] args)
						throws Throwable {
					if(method.getName().equals("close")){
						connections.addLast(conn);
						return null;
					}
					else
					{
						return method.invoke(conn,args);
					}
					
				}
				
			});
		}
		else
		{
			throw new RuntimeException("the database is busy");
		}
	}

	@Override
	public Connection getConnection(String username, String password)
			throws SQLException {
		
		return null;
	}
}
