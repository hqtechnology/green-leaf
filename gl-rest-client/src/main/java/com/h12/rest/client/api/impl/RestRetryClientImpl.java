package com.h12.rest.client.api.impl;

import com.h12.rest.client.api.AbstractRestRetryClient;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.web.client.RestTemplate;

public class RestRetryClientImpl extends AbstractRestRetryClient {
    private final RestTemplate restTemplate;
    private final RetryTemplate retryTemplate;

    public RestRetryClientImpl(RestTemplate restTemplate, RetryTemplate retryTemplate) {
        this.restTemplate = restTemplate;
        this.retryTemplate = retryTemplate;
    }

    @Override
    public RetryTemplate getRetryTemplate() {
        return retryTemplate;
    }

    @Override
    public RestTemplate getRestTemplate() {
        return restTemplate;
    }
}
