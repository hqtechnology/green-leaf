package com.h12.circuit_breaker.config;

import com.h12.circuit_breaker.callback.CallBack;
import com.h12.circuit_breaker.callback.DefaultCallBack;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.springframework.http.ResponseEntity;

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

    public <R> CallBack<Throwable, ResponseEntity<R>> getDefaultCallback(Class<R> targetClass) {
        return new DefaultCallBack<>();
    }
}
