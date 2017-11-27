package com.nilportugues.useragent.app.infrastructure.metrics;

import com.nilportugues.useragent.app.infrastructure.metrics.config.InfluxMetricsConfig;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.*;
import io.micrometer.influx.InfluxMeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class MetricsModule {

    @Bean
    public InfluxMetricsConfig getInfluxConfig() {
        return new InfluxMetricsConfig(
            "chat_metrics",
            "chat_user",
            "chat_password",
            10);
    }

    @Bean
    @Primary
    public MeterRegistry getMeterRegistry(final InfluxMetricsConfig config) {
        MeterRegistry registry = new InfluxMeterRegistry(config);
        registry = registerJVMMetrics(registry);

        return registry;
    }

    private MeterRegistry registerJVMMetrics(final MeterRegistry registry) {

        new ClassLoaderMetrics().bindTo(registry);
        new JvmMemoryMetrics().bindTo(registry);
        new JvmGcMetrics().bindTo(registry);
        new ProcessorMetrics().bindTo(registry);
        new JvmThreadMetrics().bindTo(registry);
        new LogbackMetrics().bindTo(registry);

        return registry;
    }
}
