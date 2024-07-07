package com.h12.circuit_breaker.rest.client.api;

import com.h12.circuit_breaker.callback.CallBack;
import com.h12.rest.client.api.RestRetryClient;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;

public interface RestRetryCircuitBreakerClient extends RestRetryClient {

    <R> ResponseEntity<R> httpPost(String url, String body, HashMap<String, String> headers, Class<R> rClass,
                                   CallBack<Throwable, ResponseEntity<R>> callBack);

    <R> ResponseEntity<R> httpPut(String url, String body, HashMap<String, String> headers, Class<R> rClass,
                                  CallBack<Throwable, ResponseEntity<R>> callBack);

    <R> ResponseEntity<R> httpHead(String url, String body, HashMap<String, String> headers, Class<R> rClass,
                                   CallBack<Throwable, ResponseEntity<R>> callBack);

    <R> ResponseEntity<R> httpGet(String url, HashMap<String, String> headers, Class<R> rClass,
                                  CallBack<Throwable, ResponseEntity<R>> callBack);
}
