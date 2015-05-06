package harry.tan.dao;

import harry.tan.utils.HomeWorkConstant;
import harry.tan.utils.RequestUtil;

import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

public class DB{
	private String DBDrive;
	private String DBUrl;
	private String DBUser;
	private String DBPassword;

	private void initParams() throws IOException{
		Properties p = RequestUtil.getDbParam();
		this.DBDrive = p.getProperty("username",HomeWorkConstant.EMPTY);
		this.DBUrl = p.getProperty("url",HomeWorkConstant.EMPTY);
		this.DBUser = p.getProperty("username",HomeWorkConstant.EMPTY);
		this.DBPassword = p.getProperty("password",HomeWorkConstant.EMPTY);
	}
	
	public Connection getConn(){
		try 
		{
			Class.forName(this.DBDrive);
			
		} catch (ClassNotFoundException e) {
			System.out.println("load driver failed");
		}
		
		return null;
	}
}
