package ch.shaped.microprofile.demo.greeter;

import ch.shaped.microprofile.demo.app.info.InfoResource;
import io.opentracing.Span;
import io.opentracing.Tracer;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.opentracing.ClientTracingRegistrar;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.ws.rs.client.ClientBuilder;
import java.time.temporal.ChronoUnit;

@Dependent
public class RemoteGreeterService {

    private static final Logger logger = LoggerFactory.getLogger(InfoResource.class);

    @Inject
    private Tracer tracer;

    @Inject
    @ConfigProperty(name = "GREETER_BASE", defaultValue = "http://greeter:8080")
    private String greeterBase;

    /**
     * RESTEasyClient with OpenTracing
     * Expect 75% failure on Greeter side provide Fallback
     */
    @Retry(maxRetries = 1, delay = 100, delayUnit = ChronoUnit.MILLIS, retryOn = RuntimeException.class)
    @Fallback(GreetingFallbackHandler.class)
    public Greeting getTolerantGreeter() {
        logger.info("Tolerantly fetching unreliable Greeting");

        ClientBuilder builder = ClientTracingRegistrar.configure(ResteasyClientBuilder.newBuilder());
        ResteasyClient client = ((ResteasyClientBuilder)builder).build();
        GreeterClient greeter = client.target(greeterBase).proxy(GreeterClient.class);

        Greeting greeting = null;
        Span span = tracer.buildSpan("GetTolerantGreeting").start();
        try {
            greeting = greeter.getUnreliableGreeting();
        } finally {
            client.close();
            span.finish();
        }

        return greeting;
    }

    /**
     * RESTEasyClient with OpenTracing
     * Expect 75% failure on Greeter side
     */
    public Greeting getUnreliableGreeter() {
        logger.info("Fetching unreliable Greeting");

        ClientBuilder builder = ClientTracingRegistrar.configure(ResteasyClientBuilder.newBuilder());
        ResteasyClient client = ((ResteasyClientBuilder)builder).build();
        GreeterClient greeter = client.target(greeterBase).proxy(GreeterClient.class);

        Greeting greeting = null;
        Span span = tracer.buildSpan("GetUnreliableGreeting").start();
        try {
            greeting = greeter.getUnreliableGreeting();
        } finally {
            client.close();
            span.finish();
        }

        return greeting;
    }


    /**
     * RESTEasyClient with OpenTracing
     */
    public Greeting getReliableGreeting() {
        logger.info("Fetching reliable Greeting");

        ClientBuilder builder = ClientTracingRegistrar.configure(ResteasyClientBuilder.newBuilder());
        ResteasyClient client = ((ResteasyClientBuilder)builder).build();
        GreeterClient greeter = client.target(greeterBase).proxy(GreeterClient.class);

        Greeting greeting = null;
        Span span = tracer.buildSpan("getReliableGreeting").start();
        try {
            greeting = greeter.getReliableGreeting();
        } finally {
            client.close();
            span.finish();
        }

        return greeting;
    }
}
