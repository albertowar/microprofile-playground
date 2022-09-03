package org.example;

import java.util.concurrent.atomic.AtomicLong;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;


@Readiness
@ApplicationScoped
public class CustomHealthCheck implements HealthCheck {
  private final AtomicLong readyTime = new AtomicLong(0);

  @Override
  public HealthCheckResponse call() {
    return HealthCheckResponse.named("ReadinessCheck")
        .status(true)
        .withData("time", readyTime.get())
        .build();
  }

  public void onStartUp(
      @Observes @Initialized(ApplicationScoped.class) Object init) {
    readyTime.set(System.currentTimeMillis());
  }
}
