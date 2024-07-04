package com.h12.rest.client.api.impl;

import com.h12.rest.client.api.AbstractRestClient;
import org.springframework.web.client.RestTemplate;

public class RestClientImpl extends AbstractRestClient {

    private final RestTemplate restTemplate;

    public RestClientImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public RestTemplate getRestTemplate() {
        return restTemplate;
    }
}
