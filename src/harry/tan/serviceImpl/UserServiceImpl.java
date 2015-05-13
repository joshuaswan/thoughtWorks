package harry.tan.serviceImpl;

import java.util.Map;

import harry.tan.dao.DBManager;
import harry.tan.entity.User;
import harry.tan.serviceInter.IUserService;

public class UserServiceImpl implements IUserService{
	
	/**
	 * 操作数据库类
	 */
	DBManager db = null;
	
	public UserServiceImpl(){
		db = new DBManager();
	}
	
	@Override
	public User loadUser(int userId) {
		
		final String sql = "select userId,name,sex,information from user where userId = ?";
		Map<String,Object> obj = db.loadObject(sql, userId);
		return null;
	}
	
}
