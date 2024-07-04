package com.h12.rest.client.api;

import com.h12.rest.client.api.exception.RestRetryException;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.support.RetryTemplate;

import java.util.HashMap;

public abstract class AbstractRestRetryClient extends AbstractRestClient {
    public abstract RetryTemplate getRetryTemplate();

    @Override
    public <R> ResponseEntity<R> httpPost(String url, String body, HashMap<String, String> headers, Class<R> rClass) {
        try {
            return getRetryTemplate().execute((RetryCallback<ResponseEntity<R>, Throwable>) retryContext -> super.httpPost(url, body, headers, rClass));
        } catch (Throwable e) {
            throw new RestRetryException(e);
        }
    }

    @Override
    public <R> ResponseEntity<R> httpPut(String url, String body, HashMap<String, String> headers, Class<R> rClass) {
        try {
            return getRetryTemplate().execute((RetryCallback<ResponseEntity<R>, Throwable>) retryContext -> super.httpPut(url, body, headers, rClass));
        } catch (Throwable e) {
            throw new RestRetryException(e);
        }
    }

    @Override
    public <R> ResponseEntity<R> httpHead(String url, String body, HashMap<String, String> headers, Class<R> rClass) {
        try {
            return getRetryTemplate().execute((RetryCallback<ResponseEntity<R>, Throwable>) retryContext -> super.httpHead(url, body, headers, rClass));
        } catch (Throwable e) {
            throw new RestRetryException(e);
        }
    }

    @Override
    public <R> ResponseEntity<R> httpGet(String url, HashMap<String, String> headers, Class<R> rClass) {
        try {
            return getRetryTemplate().execute((RetryCallback<ResponseEntity<R>, Throwable>) retryContext -> super.httpGet(url, headers, rClass));
        } catch (Throwable e) {
            throw new RestRetryException(e);
        }
    }
}
