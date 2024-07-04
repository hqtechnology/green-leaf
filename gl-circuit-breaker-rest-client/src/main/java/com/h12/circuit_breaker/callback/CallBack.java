package com.h12.circuit_breaker.callback;

import java.util.function.Function;

public interface CallBack<T, R> extends Function<T, R> {
}
