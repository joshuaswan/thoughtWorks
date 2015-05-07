package harry.tan.utils;

import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * <class description> 封装页面数据到model中
 * 
 * @author: harrytan
 * @version:
 */
public class RequestUtil {

    /**
     * 
     * <method description> 封装页面参数信息到实体，暂时只考虑单值
     * 
     * @param <T>
     * @param request
     * @param clazz
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws ParseException
     * @throws NoSuchMethodException
     * @throws SecurityException
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static <T> T getEntity(HttpServletRequest request, Class clazz) throws InstantiationException,
            IllegalAccessException, ParseException, SecurityException, NoSuchMethodException, IllegalArgumentException,
            InvocationTargetException {
        if (null != request.getParameterMap() && request.getParameterMap().size() > 0) {
        	
            // 反射创建一个bean对象
            Object object = clazz.newInstance();
            Method[] methods = clazz.getMethods();
            for (Method m : methods) {

                // 如果是非public方法则放弃本次操作
                if (!(m.getModifiers() == Modifier.PUBLIC)) {
                    continue;
                }

                String methodName = m.getName();

                // 存在set函数的字段可以进行对象值封装
                if (methodName.startsWith(HomeWorkConstant.MRTHOD_SET)) {
                        
                    //获取需要注入属性字段的名字
                    String fieldName = methodName.substring(HomeWorkConstant.PREFIX_LEN).substring(0, 1).toLowerCase()+methodName.substring(HomeWorkConstant.PREFIX_LEN).substring(1);
                    
                    // 请求参数里面的名字
                    String paramName = clazz.getSimpleName().toLowerCase() + HomeWorkConstant.ENTITY_DOT + fieldName;
                    
                    // 那就是对数组赋值
                    String[] paramArray = request.getParameterMap().get(paramName);
                    
                    Object entityValue = null;
                    
                    if(paramArray == null || paramArray.length < 1){
                        continue ;
                    }
                    
                    // 先不考虑传回来是字符串数组的情况
                    
                    
                 // 传入参数类型
                    Class[] paramType = m.getParameterTypes();
                    
                    if(paramType[0].toString().equals(HomeWorkConstant.INTEGER_TYPE)){
                    	entityValue = Integer.valueOf(paramArray[0]);
                    }
                    else if(paramType[0].toString().equals(HomeWorkConstant.DOUBLE_TYPE)){
                    	entityValue = Double.valueOf(paramArray[0]);
                    }
                    else if(paramType[0].toString().equals(HomeWorkConstant.DATE_TYPE)){
                    	 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                         entityValue = sdf.parseObject(paramArray[0]);
                    }
                    else
                    {
                    	entityValue = paramArray[0];
                    }
                    
                    m.invoke(object, entityValue);
                }
            }
            
            return (T) object;
        }

        return null;
    }
    
    /**
     * 读取配置文件
     * @param name
     * @return
     * @throws IOException 
     */
    public static Properties getDbParam(){
    	Properties props = new Properties();
    	InputStream in = null;
    	in = new BufferedInputStream(RequestUtil.class.getClassLoader().getResourceAsStream(HomeWorkConstant.DB_FILE_PATH));
    	try 
    	{
			props.load(in);
		} catch (IOException e) {
			System.err.println("load file is failed");
		}
    	
    	return props;
    }
    
    /**
     * 关闭资源信息
     * @param io
     */
    public static <T extends Closeable>void close(T... io){
    	for(Closeable temp : io)
		{
			if(null != null)
			{
				try 
				{
					temp.close();
				} 
				catch (IOException e) {
					
				}
			}
		}
    }
}
