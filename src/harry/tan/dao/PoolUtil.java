package harry.tan.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PoolUtil {
	private static Pool pool = new Pool();

	public static Connection getConnection() throws SQLException{
		return pool.getConnection();
	}
	
	public static int getAviableConnections(){
	    return pool.getConnectionAmount();
	}
	/**
	 * release source
	 * @param conn
	 * @param st
	 * @param rs
	 */
	public static void release(Connection conn,Statement st,ResultSet rs){
		if(null != rs){
			try 
			{
				rs.close();
			} catch (SQLException e) {
				System.err.println("release resultSet is failed");
			}
			
		if(null != st){
			try {
				st.close();
			} catch (SQLException e) {
				System.err.println("release statement is failed");
			}
		}
		
		if(null != conn){
			try {
				conn.close();
			} catch (SQLException e) {
				System.err.println("release connection is failed");
			}
		}
		
		}
	}
}
