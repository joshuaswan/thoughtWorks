package harry.tan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * <class description> 实现对数据的crud操作
 * 
 * @author: harrytan
 * @version: 1.0, May 11, 2015
 */
public class DBManager {

    private Connection        conn              = null;
    private PreparedStatement preparedStatement = null;



    /**
     * 
     * <method description> 执行查询
     * 
     * @return
     */
    public Object executeQuery(String sql, Object... objects) {
        ResultSet rs = null;
        Object obj = null;
        try {
            conn = PoolUtil.getConnection();
            preparedStatement = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            if (preparedStatement.getParameterMetaData().getParameterCount() != objects.length) {
                throw new RuntimeException("参数个数不匹配");
            }

            for (int i = 0; i < objects.length; i++) {
                preparedStatement.setObject(i+1, objects[i]);
            }
            
            boolean b = preparedStatement.execute();
            rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                obj = rs.getObject(1);
            }

            return obj;
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }
}
