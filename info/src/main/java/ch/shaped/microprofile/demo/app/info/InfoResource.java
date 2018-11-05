package ch.shaped.microprofile.demo.app.info;

import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.opentracing.Traced;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

@Path("/info")
@Tag(name = "Info Resource", description = "Providing simple information")
public class InfoResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Counted(monotonic = true, name = "info_requests", absolute = true)
    @Timed(name = "info_timed", absolute = true)
    @Operation(description = "Get informations")
    @APIResponse(responseCode = "200", description = "Successful, returning informations")
    @Traced(operationName = "GetInfo")
    public String get() throws UnknownHostException {
        return "Hello from "+ InetAddress.getLocalHost().getHostName()+" at "+ InetAddress.getLocalHost().getHostAddress();
    }

    /**
     * This way works well when there are multiple network interfaces.
     * It always returns the preferred outbound IP. The destination 8.8.8.8 is not needed to be reachable.
     * does not work on osx
     */
    private String getIP() throws UnknownHostException, SocketException {
        try(final DatagramSocket socket = new DatagramSocket()){
            socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
            return socket.getLocalAddress().getHostAddress();
        }
    }
}
