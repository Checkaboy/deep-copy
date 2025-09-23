package com.checkaboy.deepcopy.context.cache;

/**
 * @author Taras Shaptala
 */
public interface ICopyistContext<S, T> {

    T get(S source);

    void put(S source, T target);

}
