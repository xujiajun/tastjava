package cn.xujiajun.tastjava.rest;

import cn.xujiajun.tastjava.core.ioc.BeanFactory;
import cn.xujiajun.tastjava.core.ioc.ClassPathXmlApplicationContext;
import cn.xujiajun.tastjava.entity.User;
import cn.xujiajun.tastjava.provider.jwt.JWTProvider;
import cn.xujiajun.tastjava.service.UserService;
import org.pmw.tinylog.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

@Path("/user")
public class UserResource {
    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser(@PathParam("id") int id) {
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

    @Path("/login")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response login() {
        String token = JWTProvider.createToken(1);
        NewCookie cookie = new NewCookie("jwt-authToken", token,"/", "", "comment", 3600, false);
        return Response.ok("OK").cookie(cookie).build();
    }

    @GET
    @Path("/logout")
    @Produces(MediaType.TEXT_PLAIN)
    public Response logout(@CookieParam("jwt-authToken") Cookie cookie) {
        if (cookie != null) {
            NewCookie newCookie = new NewCookie("jwt-authToken", "","/", "", "comment", 0, false);
            return Response.ok("OK").cookie(newCookie).build();
        }
        return Response.ok("OK - No session").build();
    }

}
