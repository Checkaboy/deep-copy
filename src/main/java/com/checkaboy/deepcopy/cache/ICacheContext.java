package com.checkaboy.deepcopy.cache;

import java.util.IdentityHashMap;

/**
 * @author Taras Shaptala
 */
public interface ICacheContext {

    <S, T> T get(S source);

    <S, T> void put(S source, T target);

    static ICacheContext identityCache() {
        return new MapCacheContext(new IdentityHashMap<>());
    }

    void drop();

}
