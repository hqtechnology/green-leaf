package com.h12.rest.client.api;

import org.springframework.retry.support.RetryTemplate;

public interface RestRetryClient extends RestClient{
    RetryTemplate getRetryTemplate();
}
