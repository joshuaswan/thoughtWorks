package harry.tan.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;

import harry.tan.dao.PoolUtil;
import harry.tan.entity.User;

import org.junit.Test;

public class TestDao {
	
	/**
	 * 
	 */
	@Test
	public void TestConnection(){
		try {
			
			Connection conn = PoolUtil.getConnection();
			System.out.println(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
//		final User u = new User();
//		Object o = Proxy.newProxyInstance(u.getClass().getClassLoader(), u.getClass().getInterfaces(), new InvocationHandler(){
//
//			@Override
//			public Object invoke(Object proxy, Method method, Object[] args)
//					throws Throwable {
//				System.out.println(method);
//				return method.invoke(u, args);
//			}
//			
//		});
//		
//		System.out.println(o.toString());
	}
}
