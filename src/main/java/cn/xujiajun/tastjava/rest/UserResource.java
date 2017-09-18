package cn.xujiajun.tastjava.rest;

import cn.xujiajun.tastjava.core.ioc.BeanFactory;
import cn.xujiajun.tastjava.core.ioc.ClassPathXmlApplicationContext;
import cn.xujiajun.tastjava.entity.User;
import cn.xujiajun.tastjava.service.UserService;
import org.pmw.tinylog.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/user")
public class UserResource {
    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public User GetUser(@PathParam("id") int id) {
        try {
            BeanFactory factory = new ClassPathXmlApplicationContext();
            UserService userService = (UserService) factory.getBean("userService");
            User user = userService.GetUser(id);
            return user;

        } catch (Exception e) {
            Logger.error(e.toString());
//            return "Exception:"+e.toString()+". msg:"+e.getMessage()+". trace:"+e.getStackTrace();
        }
        return null;
    }


}
