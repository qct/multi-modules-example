package qct.rest;

import javax.jws.WebMethod;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by alex on 2014/8/23.
 */
@Path("/hello")
public interface HelloRest {

    @Path("/sayHello")
    @WebMethod
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    Response sayHello();

    @Path("/sayHelloXml")
    @WebMethod
    @GET
    @Produces(MediaType.APPLICATION_XML)
    Response sayHelloXml();

    /**
     * http://localhost:8080/qct-webservice/rest/hello/sayHelloByExtensions  return xml
     * http://localhost:8080/qct-webservice/rest/hello/sayHelloByExtensions.json return json
     * http://localhost:8080/qct-webservice/rest/hello/sayHelloByExtensions.xml return xml
     * @return
     */
    @Path("/sayHelloByExtensions")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes(MediaType.APPLICATION_JSON)
    Response sayHelloByExtensions();
}
