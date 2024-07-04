package com.h12.circuit_breaker.config;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;

public class GLCircuitBreakerConfig {
    private static final CircuitBreakerRegistry registry;
    private static final CircuitBreakerConfig circuitBreakerConfig;

    static {
        circuitBreakerConfig = CircuitBreakerConfig.ofDefaults();
        registry = CircuitBreakerRegistry.of(circuitBreakerConfig);
    }

    public static CircuitBreaker circuitBreaker(String serviceName) {
        return registry.circuitBreaker(serviceName);
    }
}
