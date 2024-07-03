package com.h12.web.client.api;

import org.apache.commons.collections4.MapUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractRestClient implements RestClient {

    public abstract RestTemplate getRestTemplate();


    public MultiValueMap<String, String> buildHeaders(HashMap<String, String> headers) {
        HttpHeaders httpHeaders = new HttpHeaders();
        if(!MapUtils.isEmpty(headers)) {
            for (Map.Entry<String, String> entry: headers.entrySet()) {
                httpHeaders.add(entry.getKey(), entry.getValue());
            }
        }
        return httpHeaders;
    }

    @Override
    public <R> ResponseEntity<R> httpPost(String url, String body, HashMap<String, String> headers, Class<R> rClass) {
        HttpEntity<String> request = new HttpEntity<>(body, buildHeaders(headers));
        return getRestTemplate().postForEntity(url, request, rClass);
    }

    @Override
    public <R> ResponseEntity<R> httpPut(String url, String body, HashMap<String, String> headers, Class<R> rClass) {
        HttpEntity<String> request = new HttpEntity<>(body, buildHeaders(headers));
        return getRestTemplate().exchange(url, HttpMethod.PUT, request, rClass);
    }

    @Override
    public <R> ResponseEntity<R> httpHead(String url, String body, HashMap<String, String> headers, Class<R> rClass) {
        HttpEntity<String> request = new HttpEntity<>("", buildHeaders(headers));
        return getRestTemplate().exchange(url, HttpMethod.PUT, request, rClass);
    }

    @Override
    public <R> ResponseEntity<R> httpGet(String url, HashMap<String, String> headers, Class<R> rClass) {
        return null;
    }
}
