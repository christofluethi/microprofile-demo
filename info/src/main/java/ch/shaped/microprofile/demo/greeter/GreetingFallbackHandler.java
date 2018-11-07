package ch.shaped.microprofile.demo.greeter;

import ch.shaped.microprofile.demo.app.info.InfoResource;
import org.eclipse.microprofile.faulttolerance.ExecutionContext;
import org.eclipse.microprofile.faulttolerance.FallbackHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GreetingFallbackHandler implements FallbackHandler<Greeting> {

    private static final Logger logger = LoggerFactory.getLogger(InfoResource.class);

    @Override
    public Greeting handle(ExecutionContext executionContext) {
        logger.info("Greeting FallbackHandler");

        Greeting g = new Greeting();
        g.setGreeting("unreachable");

        return g;
    }
}
