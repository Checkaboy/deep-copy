package com.checkaboy.deepcopy.cache;

import java.util.function.Supplier;

/**
 * @author Taras Shaptala
 */
public interface ICacheContext<S, T, K> {

    T get(S source);

    void put(S source, T target);

    T putIfAbsent(S source, Supplier<T> constructor);

    void drop();

}
