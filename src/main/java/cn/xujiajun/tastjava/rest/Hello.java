package cn.xujiajun.tastjava.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hi")
public class Hello {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getMessage() {
        return "Hello world!";
    }
}