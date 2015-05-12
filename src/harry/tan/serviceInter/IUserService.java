package harry.tan.serviceInter;

import harry.tan.entity.User;

/**
 * 用户操作类
 * @author Administrator
 *
 */
public interface IUserService {
	/**
	 * 加载单个用户信息
	 * @param userId
	 * @return
	 */
	public User loadUser(int userId);
	
}
