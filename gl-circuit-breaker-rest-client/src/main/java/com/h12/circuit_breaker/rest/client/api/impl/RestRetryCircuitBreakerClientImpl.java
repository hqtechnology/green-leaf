package com.h12.circuit_breaker.rest.client.api.impl;

import com.h12.circuit_breaker.callback.CallBack;
import com.h12.circuit_breaker.config.GLCircuitBreakerConfig;
import com.h12.circuit_breaker.rest.client.api.AbstractRestRetryCircuitBreakerClient;
import com.h12.rest.client.api.RestRetryClient;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.core.SupplierUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.function.Supplier;

public class RestRetryCircuitBreakerClientImpl extends AbstractRestRetryCircuitBreakerClient implements RestRetryClient {
    private final CircuitBreaker circuitBreaker;
    private final RestTemplate restTemplate;
    private final RetryTemplate retryTemplate;

    public RestRetryCircuitBreakerClientImpl(RestTemplate restTemplate, RetryTemplate retryTemplate,
                                             String nameOfService) {
        this(restTemplate, retryTemplate, GLCircuitBreakerConfig.circuitBreaker(nameOfService));
    }

    public RestRetryCircuitBreakerClientImpl(RestTemplate restTemplate, RetryTemplate retryTemplate,
                                             CircuitBreaker circuitBreaker) {
        this.circuitBreaker = circuitBreaker;
        this.restTemplate = restTemplate;
        this.retryTemplate = retryTemplate;
    }


    public <R> ResponseEntity<R> httpPost(String url, String body, HashMap<String, String> headers, Class<R> rClass,
                                          CallBack<Throwable, ResponseEntity<R>> callBack) {
        Supplier<ResponseEntity<R>> restSupplier = () -> super.httpPost(url, body, headers, rClass);
        Supplier<ResponseEntity<R>> supplierWithResult = SupplierUtils.recover(restSupplier, callBack);
        return circuitBreaker.executeSupplier(supplierWithResult);
    }

    @Override
    public <R> ResponseEntity<R> httpPut(String url, String body, HashMap<String, String> headers, Class<R> rClass, CallBack<Throwable, ResponseEntity<R>> callBack) {
        Supplier<ResponseEntity<R>> restSupplier = () -> super.httpPut(url, body, headers, rClass);
        Supplier<ResponseEntity<R>> supplierWithResult = SupplierUtils.recover(restSupplier, callBack);
        return circuitBreaker.executeSupplier(supplierWithResult);
    }

    @Override
    public <R> ResponseEntity<R> httpHead(String url, String body, HashMap<String, String> headers, Class<R> rClass, CallBack<Throwable, ResponseEntity<R>> callBack) {
        Supplier<ResponseEntity<R>> restSupplier = () -> super.httpHead(url, body, headers, rClass);
        Supplier<ResponseEntity<R>> supplierWithResult = SupplierUtils.recover(restSupplier, callBack);
        return circuitBreaker.executeSupplier(supplierWithResult);
    }

    @Override
    public <R> ResponseEntity<R> httpGet(String url, HashMap<String, String> headers, Class<R> rClass, CallBack<Throwable, ResponseEntity<R>> callBack) {
        Supplier<ResponseEntity<R>> restSupplier = () -> super.httpGet(url, headers, rClass);
        Supplier<ResponseEntity<R>> supplierWithResult = SupplierUtils.recover(restSupplier, callBack);
        return circuitBreaker.executeSupplier(supplierWithResult);
    }


    @Override
    public RestTemplate getRestTemplate() {
        return this.restTemplate;
    }

    @Override
    public RetryTemplate getRetryTemplate() {
        return this.retryTemplate;
    }
}
