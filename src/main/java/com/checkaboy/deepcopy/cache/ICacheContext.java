package com.checkaboy.deepcopy.cache;

/**
 * @author Taras Shaptala
 */
public interface ICacheContext {

    <S, T> T get(S source);

    <S, T> void put(S source, T target);

    void drop();

}
