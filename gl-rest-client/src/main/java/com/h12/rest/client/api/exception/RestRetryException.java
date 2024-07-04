package com.h12.rest.client.api.exception;

public class RestRetryException extends RuntimeException {
    public RestRetryException() {
        super();
    }

    public RestRetryException(String message) {
        super(message);
    }

    public RestRetryException(String message, Throwable cause) {
        super(message, cause);
    }

    public RestRetryException(Throwable cause) {
        super(cause);
    }

    protected RestRetryException(String message, Throwable cause,
                                 boolean enableSuppression,
                                 boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
