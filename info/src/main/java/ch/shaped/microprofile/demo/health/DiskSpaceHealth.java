package ch.shaped.microprofile.demo.health;

import ch.shaped.microprofile.demo.util.Usage;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.health.Health;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.File;

@Health
@ApplicationScoped
public class DiskSpaceHealth implements HealthCheck {

    @Inject
    @ConfigProperty(name = "health.diskSpace.path", defaultValue = "/")
    private String configPath;

    @Inject
    @ConfigProperty(name = "health.diskSpace.thresholdPercent", defaultValue = "90.0")
    private String configThresholdPercent;

    @Override
    public HealthCheckResponse call() {
        File path = new File(configPath);
        Double thresholdPercent = Double.valueOf(configThresholdPercent);

        long free = path.getUsableSpace();
        long total = path.getTotalSpace();
        long used = total - free;
        long threshold = (long) ((thresholdPercent / 100) * total);

        return HealthCheckResponse.named("diskSpace")
                .state(threshold >= used)
                .withData("free", Usage.format(free))
                .withData("freeBytes", free)
                .withData("freePercent", String.format("%.2f%%", 100 * ((double) free / total)))
                .withData("total", Usage.format(total))
                .withData("totalBytes", total)
                .build();
    }
}
