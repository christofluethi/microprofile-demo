package ch.shaped.microprofile.demo.health;

import org.eclipse.microprofile.health.Health;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;

import javax.enterprise.context.ApplicationScoped;

@Health
@ApplicationScoped
public class PingHealth implements HealthCheck {

        @Override
        public HealthCheckResponse call() {
            return HealthCheckResponse.named("ping").up().build();
        }
}
