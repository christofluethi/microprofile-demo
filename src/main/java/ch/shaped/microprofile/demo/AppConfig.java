package ch.shaped.microprofile.demo;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/api")
public class AppConfig extends Application {

    public AppConfig() {

    }
}
