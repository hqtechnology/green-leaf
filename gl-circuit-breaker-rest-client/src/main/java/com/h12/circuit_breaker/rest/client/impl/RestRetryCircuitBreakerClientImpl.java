package com.h12.circuit_breaker.rest.client.impl;

import com.h12.circuit_breaker.callback.CallBack;
import com.h12.circuit_breaker.config.GLCircuitBreakerConfig;
import com.h12.rest.client.api.impl.RestRetryClientImpl;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.core.SupplierUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.function.Supplier;

public class RestRetryCircuitBreakerClientImpl extends RestRetryClientImpl {
    private final CircuitBreaker circuitBreaker;

    public RestRetryCircuitBreakerClientImpl(RestTemplate restTemplate, RetryTemplate retryTemplate,
                                             String nameOfService) {
        this(restTemplate, retryTemplate, GLCircuitBreakerConfig.circuitBreaker(nameOfService));
    }

    public RestRetryCircuitBreakerClientImpl(RestTemplate restTemplate, RetryTemplate retryTemplate,
                                             CircuitBreaker circuitBreaker) {
        super(restTemplate, retryTemplate);
        this.circuitBreaker = circuitBreaker;
    }


    public <R> ResponseEntity<R> httpPost(String url, String body, HashMap<String, String> headers, Class<R> rClass,
                                          CallBack<ResponseEntity<R>, ResponseEntity<R>> callBack) {
        Supplier<ResponseEntity<R>> restSupplier = () -> super.httpPost(url, body, headers, rClass);
        Supplier<ResponseEntity<R>> supplierWithResult = SupplierUtils.andThen(restSupplier, callBack);
        return circuitBreaker.executeSupplier(supplierWithResult);
    }

}
