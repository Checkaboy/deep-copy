package com.checkaboy.deepcopy.cache.builder;

import com.checkaboy.deepcopy.cache.CacheContext;
import com.checkaboy.deepcopy.cache.ICacheContext;

import java.util.Map;
import java.util.function.Function;

/**
 * @author Taras Shaptala
 */
public class CacheContextBuilder<S, T, K>
        implements ICacheContextBuilder<S, T, K> {

    private Map<K, T> cache;
    private Function<S, K> keyExtractor;

    @Override
    public ICacheContextBuilder<S, T, K> setCache(Map<K, T> cache) {
        this.cache = cache;
        return this;
    }

    @Override
    public ICacheContextBuilder<S, T, K> setKeyExtractor(Function<S, K> keyExtractor) {
        this.keyExtractor = keyExtractor;
        return this;
    }

    public ICacheContext<S, T, K> build() {
        return new CacheContext<>(cache, keyExtractor);
    }

}
