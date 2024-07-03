package com.h12.web.client.api;


public class RestRetryConfig extends RestConfig {
    private Integer totalMaxRequests;
    private Integer readTimeout;
    private Integer retryCount;
    private Integer retryBackOffTime;
}
