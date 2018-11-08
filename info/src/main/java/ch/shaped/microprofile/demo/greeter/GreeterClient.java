package ch.shaped.microprofile.demo.greeter;

import org.eclipse.microprofile.openapi.annotations.Operation;

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
    @Operation(hidden = true)
    Greeting getReliableGreeting();

    @GET
    @Path("/unreliable")
    @Operation(hidden = true)
    Greeting getUnreliableGreeting();
}
