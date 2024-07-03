package com.h12.web.client.api;

import org.springframework.http.ResponseEntity;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

public abstract class AbstractRestRetryClient extends AbstractRestClient {
    public abstract RetryTemplate getRetryTemplate();

    @Override
    public <R> ResponseEntity<R> httpPost(String url, String body, HashMap<String, String> headers, Class<R> rClass) {
        try {
            return getRetryTemplate().execute((RetryCallback<ResponseEntity<R>, Throwable>) retryContext -> super.httpPost(url, body, headers, rClass));
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <R> ResponseEntity<R> httpPut(String url, String body, HashMap<String, String> headers, Class<R> rClass) {
        return null;
    }

    @Override
    public <R> ResponseEntity<R> httpHead(String url, String body, HashMap<String, String> headers, Class<R> rClass) {
        return null;
    }

    @Override
    public <R> ResponseEntity<R> httpGet(String url, HashMap<String, String> headers, Class<R> rClass) {
        return null;
    }
}
