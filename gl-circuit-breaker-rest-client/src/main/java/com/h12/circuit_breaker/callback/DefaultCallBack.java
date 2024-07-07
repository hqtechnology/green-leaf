package com.h12.circuit_breaker.callback;

import org.springframework.http.ResponseEntity;

public class DefaultCallBack<T> implements CallBack<Throwable, ResponseEntity<T>> {
    @Override
    public ResponseEntity<T> apply(Throwable throwable) {
        return ResponseEntity.internalServerError().build();
    }
}
