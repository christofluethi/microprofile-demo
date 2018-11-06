package ch.shaped.microprofile.demo.greeter.health;

import org.eclipse.microprofile.health.Health;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;

import javax.enterprise.context.ApplicationScoped;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

@Health
@ApplicationScoped
public class DeadlockThreadHealth implements HealthCheck {

    @Override
    public HealthCheckResponse call() {
        ThreadMXBean threadMxBean = ManagementFactory.getThreadMXBean();

        return HealthCheckResponse.named("deadlockThread")
                .state(threadMxBean.findDeadlockedThreads() == null)
                .build();
    }
}
