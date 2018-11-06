package ch.shaped.microprofile.demo.greeter.ws;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {
    @Override
    public Response toResponse(NotFoundException e) {
        return Response
                .status(Response.Status.OK)
                .entity("He's dead, Jim")
                .type(MediaType.TEXT_PLAIN_TYPE)
                .build();
    }
}
