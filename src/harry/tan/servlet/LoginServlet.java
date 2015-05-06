package harry.tan.servlet;

import harry.tan.entity.User;
import harry.tan.utils.RequestUtil;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;



    @Override
    protected void doGet(HttpServletRequest pReq, HttpServletResponse pResp) throws ServletException, IOException {
        this.doPost(pReq, pResp);
    }



    @Override
    protected void doPost(HttpServletRequest pReq, HttpServletResponse pResp) throws ServletException, IOException {
        try {
            User user = RequestUtil.getEntity(pReq, User.class);
            
            System.out.println(user.getName());
            System.out.println(user.getAge());
            System.out.println(user.getBirthDay());
            
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void destroy() {
        super.destroy();
    }



    @Override
    public void init() throws ServletException {
        super.init();
    }

}
