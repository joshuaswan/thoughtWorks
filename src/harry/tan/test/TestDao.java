package harry.tan.test;

import java.sql.Connection;
import java.sql.SQLException;

import harry.tan.dao.PoolUtil;
import harry.tan.serviceImpl.UserServiceImpl;
import harry.tan.serviceInter.IUserService;

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
			
			conn.close();
			
			System.out.println(PoolUtil.getAviableConnections());
			conn.close();
			PoolUtil.getAviableConnections();
			System.out.println(PoolUtil.getAviableConnections());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void TestUserService(){
		IUserService s  = new UserServiceImpl();
		s.loadUser(1);
	}
}
