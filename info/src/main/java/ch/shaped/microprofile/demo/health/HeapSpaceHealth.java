package ch.shaped.microprofile.demo.health;

import ch.shaped.microprofile.demo.util.Usage;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.health.Health;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@Health
@ApplicationScoped
public class HeapSpaceHealth implements HealthCheck {

    @Inject
    @ConfigProperty(name = "health.heapSpace.thresholdPercent", defaultValue = "0.95")
    private String configThresholdPercent;

    @Override
    public HealthCheckResponse call() {
        Double thresholdPercent = Double.valueOf(configThresholdPercent);

        Runtime runtime = Runtime.getRuntime();
        final long free = runtime.freeMemory();
        final long total = runtime.totalMemory();
        final long max = runtime.maxMemory();
        final long used = total - free;
        final long threshold = (long) (thresholdPercent * max);

        return HealthCheckResponse.named("heapSpace")
                .state(threshold >= used)
                .withData("free", Usage.format(free))
                .withData("freeBytes", free)
                .withData("freePercent", String.format("%.2f%%", 100 * ((double) (max - used) / max)))
                .withData("max", Usage.format(max))
                .withData("maxBytes", max)
                .withData("total", Usage.format(total))
                .withData("totalBytes", total)
                .build();
    }
}
