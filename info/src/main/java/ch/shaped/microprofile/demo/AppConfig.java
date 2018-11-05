package ch.shaped.microprofile.demo;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.servers.Server;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@OpenAPIDefinition(info = @Info(
        title = "Microprofile Thorntail Demo",
        version = "1.0.0",
        contact = @Contact(
                name = "Christof Luethi",
                url = "http://www.shaped.ch/",
                email = "example@example.com")),
        servers = {
                @Server(url="/", description = "localhost"),
        }
)
@ApplicationPath("/resource")
public class AppConfig extends Application {

    public AppConfig() {

    }
}
