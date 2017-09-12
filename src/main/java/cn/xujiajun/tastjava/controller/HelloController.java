package cn.xujiajun.tastjava.controller;

import com.alibaba.fastjson.JSON;
import cn.xujiajun.tastjava.core.ioc.BeanFactory;
import cn.xujiajun.tastjava.core.ioc.ClassPathXmlApplicationContext;
import cn.xujiajun.tastjava.entity.User;
import cn.xujiajun.tastjava.service.UserService;
import org.pmw.tinylog.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Properties;

@WebServlet("/hello")
public class HelloController extends HttpServlet {
    private String message;

    @Override
    public void init() throws ServletException {
        message = "hi,tastjava!";
        System.out.println("servlet初始化……");
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        Logger.info("HelloController");
        try {

            BeanFactory factory = new ClassPathXmlApplicationContext();
//            out.println("<h3>BeanFactory</h3>");
            //通过工厂直接获取
            UserService userService = (UserService) factory.getBean("userService");
//            out.println("<h3>userService</h3>");
            List<User> users = userService.GetUsers();
//            out.println("<h3>userService GetUsers</h3>");
//
//            out.println("<h3>user.dir:</h3>");
//
//            out.println(System.getProperty("user.dir"));

            String usersJson = JSON.toJSONString(users);

            out.println(usersJson);
        } catch (Exception e) {
            // Handle it.
            out.println("<h3>Exception</h3>");
            out.println("<h3>" + e.getMessage() + "</h3>");

            e.printStackTrace();
            out.println("------------------------------");
            for (StackTraceElement elem : e.getStackTrace()) {
                out.println("<br>");
                out.println(elem);
            }
        }

        out.println("<h3>" + message + "</h3>");
    }

    @Override
    public void destroy() {
        System.out.println("servlet销毁！");
        super.destroy();
    }
}