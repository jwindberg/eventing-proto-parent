package com.marsraver;

import io.grpc.health.v1.HealthCheckRequest;
import io.grpc.health.v1.HealthCheckResponse;
import io.grpc.health.v1.HealthGrpc;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.Health.Builder;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.boot.availability.AvailabilityChangeEvent;
import org.springframework.boot.availability.LivenessState;
import org.springframework.boot.availability.ReadinessState;
import org.springframework.context.ApplicationContext;

import javax.annotation.PostConstruct;

@Slf4j
//@Component
public class GrpcServerHealthIndicator implements HealthIndicator {

    private ApplicationContext applicationContext;
    @GrpcClient("Health")
    private HealthGrpc.HealthBlockingStub healthStub;
    private boolean publishAvailabilityChangeEvent;

    public GrpcServerHealthIndicator(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;

    }

    @PostConstruct
    public void postConstruct() {
        publishAvailabilityChangeEvent =
                applicationContext.containsBean("livenessStateHealthIndicator")
                        && applicationContext.containsBean("readinessStateHealthIndicator");
        if (publishAvailabilityChangeEvent) {
            log.info("Kubernetes probes are enabled");
        } else {
            log.info("Kubernetes probes not enabled");
        }
    }


    @Override
    public Health health() {

        HealthCheckRequest request = HealthCheckRequest.getDefaultInstance();
        Builder healthBuilder = new Builder();
        try {
            HealthCheckResponse healthCheckResponse = healthStub.check(request);
            switch (healthCheckResponse.getStatus()) {
                case SERVING -> healthBuilder.up();
                case NOT_SERVING -> healthBuilder.outOfService();
                default -> healthBuilder.down();
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            healthBuilder.down();
        }

        Health health = healthBuilder.build();

        if (publishAvailabilityChangeEvent) {

            if (Status.UP == health.getStatus()) {
                AvailabilityChangeEvent.publish(applicationContext, applicationContext,
                        LivenessState.CORRECT);
                AvailabilityChangeEvent.publish(applicationContext, applicationContext,
                        ReadinessState.ACCEPTING_TRAFFIC);
            } else if (Status.OUT_OF_SERVICE == health.getStatus()) {
                AvailabilityChangeEvent.publish(applicationContext, applicationContext,
                        ReadinessState.REFUSING_TRAFFIC);
            } else {
                AvailabilityChangeEvent.publish(applicationContext, applicationContext,
                        LivenessState.BROKEN);
            }
        }

        return health;
    }


}
