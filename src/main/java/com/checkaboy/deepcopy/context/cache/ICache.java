package com.checkaboy.deepcopy.context.cache;

import com.checkaboy.deepcopy.context.key.ClassKey;

/**
 * @author Taras Shaptala
 */
public interface ICache {

    <S, T> ICacheContext<S, T> get(ClassKey classKey);

    <S, T> void put(ClassKey classKey, ICacheContext<S, T> cacheContext);

}
