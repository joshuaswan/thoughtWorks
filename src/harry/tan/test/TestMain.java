package harry.tan.test;

import harry.tan.entity.Person;
import harry.tan.entity.User;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TestMain {

    /**
     * <method description>
     *
     * @param args
     */

    public static void main(String[] args) {
        
       final User u = new User();
       Person p = (Person)Proxy.newProxyInstance(TestMain.class.getClassLoader(),u.getClass().getInterfaces(), new InvocationHandler(){

        @Override
        public Object invoke(Object pProxy, Method pMethod, Object[] pArgs) throws Throwable {
            
            return pMethod.invoke(u, pArgs);
        }
           
       });
       

    }

}
