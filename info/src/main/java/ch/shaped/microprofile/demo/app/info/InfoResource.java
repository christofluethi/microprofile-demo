package ch.shaped.microprofile.demo.app.info;

import ch.shaped.microprofile.demo.model.Information;
import ch.shaped.microprofile.demo.util.InformationBuilder;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.opentracing.Traced;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Path("/info")
@Tag(name = "Info Resource", description = "Providing simple information")
@ApplicationScoped
public class InfoResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Counted(monotonic = true, name = "info_requests", absolute = true)
    @Timed(name = "info_timed", absolute = true)
    @Operation(description = "Get informations")
    @APIResponse(
            responseCode = "200",
            description = "Successful, returning informations",
            content = @Content(mediaType = MediaType.APPLICATION_JSON,
            schema = @Schema(implementation = Information.class))
    )
    @Traced(operationName = "GetInfo")
    public Response get() throws UnknownHostException {
        Config config = ConfigProvider.getConfig();
        InformationBuilder builder = new InformationBuilder();

        config.getOptionalValue("os.name", String.class).ifPresent(builder::osName);
        config.getOptionalValue("os.arch", String.class).ifPresent(builder::osArch);
        config.getOptionalValue("os.version", String.class).ifPresent(builder::osVersion);

        builder.ip(InetAddress.getLocalHost().getHostAddress());
        builder.hostname(InetAddress.getLocalHost().getHostName());

        return Response.ok(builder.build()).build();
    }
}
