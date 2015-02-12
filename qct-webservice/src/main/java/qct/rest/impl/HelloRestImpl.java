package qct.rest.impl;

import qct.pojo.User;
import qct.rest.HelloRest;

import javax.ws.rs.core.Response;

/**
 * Created by alex on 2014/8/23.
 */
public class HelloRestImpl implements HelloRest{
    @Override
    public Response sayHello() {
        User user = new User();
        user.setAge(28);
        user.setName("quchentao");
        return Response.status(Response.Status.OK).entity(user).build();
    }

    @Override
    public Response sayHelloXml() {
        User user = new User();
        user.setAge(28);
        user.setName("quchentao");
        return Response.status(Response.Status.OK).entity(user).build();
    }

    @Override
    public Response sayHelloByExtensions() {
        User user = new User();
        user.setAge(29);
        user.setName("quchentao");
        return Response.status(Response.Status.OK).entity(user).build();
    }
}
