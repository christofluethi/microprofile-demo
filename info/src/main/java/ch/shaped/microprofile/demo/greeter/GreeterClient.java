package ch.shaped.microprofile.demo.greeter;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/resource/greeting")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface GreeterClient {

    @GET
    @Path("/reliable")
    Greeting getReliableGreeting();

    @GET
    @Path("/unreliable")
    Greeting getUnreliableGreeting();
}
