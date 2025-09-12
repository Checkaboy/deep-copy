package com.checkaboy.deepcopy.context.cache;

/**
 * @author Taras Shaptala
 */
public interface ICache {

    <S, T> ICacheContext<S, T> get(Object object);

}
