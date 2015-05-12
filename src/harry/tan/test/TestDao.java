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
<<<<<<< HEAD
			System.out.println(conn);
			
			conn.close();
			
=======
			System.out.println(PoolUtil.getAviableConnections());
			conn.close();
			PoolUtil.getAviableConnections();
			System.out.println(PoolUtil.getAviableConnections());
>>>>>>> fa398f2226796b746f6b8b7b1c52159d06927829
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
