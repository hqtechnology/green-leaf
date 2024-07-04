package com.h12.rest.client.api;

import org.springframework.http.ResponseEntity;

import java.util.HashMap;

public interface RestClient {
    <R> ResponseEntity<R> httpPost(String url, String body, HashMap<String, String> headers, Class<R> rClass);
    <R> ResponseEntity<R> httpPut(String url, String body, HashMap<String, String> headers, Class<R> rClass);
    <R> ResponseEntity<R> httpHead(String url, String body, HashMap<String, String> headers, Class<R> rClass);
    <R> ResponseEntity<R> httpGet(String url, HashMap<String, String> headers, Class<R> rClass);

}
