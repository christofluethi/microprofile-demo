package ch.shaped.microprofile.demo;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/resource")
public class AppConfig extends Application {

    public AppConfig() {

    }
}
