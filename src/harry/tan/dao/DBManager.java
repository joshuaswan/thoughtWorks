package harry.tan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * <method description> 执行插入
     * 
     * @return
     */
    public Object executeUpdate(String sql, Object... objects) {
        ResultSet rs = null;
        Object obj = null;
        try {
            conn = PoolUtil.getConnection();
            preparedStatement = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            if (preparedStatement.getParameterMetaData().getParameterCount() != objects.length) {
                throw new RuntimeException("参数个数不匹配");
            }

            for (int i = 0; i < objects.length; i++) {
                preparedStatement.setObject(i + 1, objects[i]);
            }

            boolean bool = preparedStatement.execute();
            if (bool) {
                rs = preparedStatement.getGeneratedKeys();
                if (rs.next()) {
                    obj = rs.getObject(1);
                }

                return obj;
            }

            return null;
        } catch (SQLException e) {
            System.out.println(e);
        }
        finally{
            PoolUtil.release(conn,preparedStatement,rs);
        }
        
        return null;
    }
    
    /**
     * 
    * <method description>
    *   主要执行查询操作
    * @param sql
    * @param objects
    * @return
     */
    public List<Map<String,Object>> executeQuery(String sql, Object... objects){
        ResultSet rs = null;
        List<Map<String,Object>> listMap = null;
        try {
            conn = PoolUtil.getConnection();
            preparedStatement = conn.prepareStatement(sql);
            if(preparedStatement.getMetaData().getColumnCount() != objects.length){
                throw new RuntimeException("参数个数不匹配");
            }
            
            for (int i = 0; i < objects.length; i++) {
                preparedStatement.setObject(i + 1, objects[i]);
            }
            
            rs = preparedStatement.executeQuery();
            if(null != rs){
                
                // 有必要去实例化listMap
                listMap = new ArrayList<Map<String,Object>>();
                while(rs.next()){
                    for(int i=0;i<rs.getMetaData().getColumnCount();i++){
                        Map<String,Object> map = new HashMap<String, Object>();
                        final String columName = rs.getMetaData().getColumnName(i+1);
                        final Object columValue = rs.getObject(columName);
                        map.put(columName, columValue);
                        listMap.add(map);
                    }
                }
                
                return listMap;
            }
            
            // rs为null，直接返回null
            return null;
            
        } catch (SQLException e) {
           System.out.println("get the connection is failed");
        }
        finally{
            PoolUtil.release(conn,preparedStatement,rs);
        }
        
        return null;
    }

}
