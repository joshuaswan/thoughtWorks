package harry.tan.test;

import java.sql.Connection;
import java.sql.SQLException;

import harry.tan.dao.PoolUtil;

import org.junit.Test;

public class TestDao {
	
	/**
	 * 
	 */
	@Test
	public void TestConnection(){
		try {
			
			Connection conn = PoolUtil.getConnection();
			System.out.println(PoolUtil.getAviableConnections());
			conn.close();
			PoolUtil.getAviableConnections();
			System.out.println(PoolUtil.getAviableConnections());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
