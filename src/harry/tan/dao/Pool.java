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
				final Connection conn = DriverManager.getConnection(DBUrl, DBUser, DBPassword);
				
				// 动态代理实现调用close方法自动添加到连接池
				Connection connfinal = (Connection)Proxy.newProxyInstance(Pool.class.getClassLoader(), new Class[]{Connection.class}, new InvocationHandler() {
                    @Override
                    public Object invoke(Object pProxy, Method pMethod, Object[] pArgs) throws Throwable {
                        if(pMethod.getName().equals("close")){
                            connections.addLast(conn);
                            return null;
                        }
                        else
                        {
                            return pMethod.invoke(conn,pArgs);
                        }
                    }
                });
				
				connections.add(connfinal);
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
		    return connections.removeFirst();
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
	
	public int getConnectionAmount(){
	    return connections.size();
	} 
}
