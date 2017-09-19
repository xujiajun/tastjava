package cn.xujiajun.tastjava.rest;

import org.pmw.tinylog.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Path("/hi")
public class HelloResource {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getMessage() {
        return "Hello world!";
    }

    @Path("/{name}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getName(@PathParam("name") String name) {
        return "Hello," + name;
    }

    @POST
    @Path("/add")
    @Produces(MediaType.TEXT_PLAIN)
    public String add(@FormParam("name") String name) {
        return "add name:" + name;
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String remove(@FormParam("id") int id) {
        return "remove id:" + id;
    }

    @PUT
    @Path("/update")
    @Produces(MediaType.TEXT_PLAIN)
    public String update(@FormParam("name") String name) {
        return "update name:" + name;
    }

    @GET
    @Path("/json")
    @Produces(MediaType.APPLICATION_JSON)
    public List<MyCustomBean> sayHiWithJson() {
        List<MyCustomBean> ListMyCustomBean = new ArrayList<MyCustomBean>();
        MyCustomBean beanOne = new MyCustomBean();
        beanOne.setAge(27);
        beanOne.setName("johnny");
        MyCustomBean beanTwo = new MyCustomBean();
        beanTwo.setAge(28);
        beanTwo.setName("jersey");
        ListMyCustomBean.add(beanOne);
        ListMyCustomBean.add(beanTwo);
        return ListMyCustomBean;
    }

    @GET
    @Path("/path/{id}")
    public String sayWithParam(@PathParam("id") int id) {
        return "get param id value:" + id;
    }

    @GET
    @Path("/context/{id}")
    public String sayTestContext(@Context UriInfo ui,@PathParam("id") int id) {
        Logger.info(ui.getPathParameters());
        return ui.getPath();
    }
}


class MyCustomBean implements Serializable {

    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}