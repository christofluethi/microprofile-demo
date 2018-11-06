package ch.shaped.microprofile.demo.greeter.app.greeter;

import ch.shaped.microprofile.demo.greeter.model.Greeting;
import io.opentracing.Span;
import io.opentracing.Tracer;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.opentracing.Traced;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Path("/greeting")
@Tag(name = "Greeting Resource", description = "Just a friendly service")
public class GreeterResource {

    @Inject
    private Tracer tracer;

    @Inject
    @ConfigProperty(name = "GREETER_SLEEP", defaultValue = "10")
    private String greeterSleep;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Counted(monotonic = true, name = "greeting_requests", absolute = true)
    @Timed(name = "greeting_timed", absolute = true)
    @Operation(description = "Get Greeting")
    @APIResponse(responseCode = "200",
            description = "Successful, returning friendly greeting",
            content = @Content(mediaType = MediaType.APPLICATION_JSON,
            schema = @Schema(implementation = Greeting.class))
    )
    @Traced(operationName = "GetGreeting")
    public Response get() throws UnknownHostException, InterruptedException {
        return Response.ok(new Greeting(buildGreetingString())).build();
    }

    private String buildGreetingString() throws UnknownHostException, InterruptedException {
        Span span = tracer.buildSpan("buildGreeting").start();
        // fake invocation time
        Thread.sleep(Integer.parseInt(greeterSleep));
        String s = "Hello from '"+InetAddress.getLocalHost().getHostName()+"' running on "+ InetAddress.getLocalHost().getHostAddress();
        span.finish();
        return s;
    }
}
