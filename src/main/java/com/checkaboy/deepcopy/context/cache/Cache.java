package com.checkaboy.deepcopy.context.cache;

import com.checkaboy.deepcopy.context.factory.ICacheContextFactory;

import java.util.IdentityHashMap;
import java.util.Map;

/**
 * @author Taras Shaptala
 */
public class Cache
        implements ICache {

    private final Map<Object, ICacheContext<?, ?>> cacheContextMap = new IdentityHashMap<>();
    private final ICacheContextFactory cacheContextFactory;

    public Cache() {
        cacheContextFactory = CacheContest::new;
    }

    public Cache(ICacheContextFactory cacheContextFactory) {
        this.cacheContextFactory = cacheContextFactory;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <S, T> ICacheContext<S, T> get(Object object) {
        return (ICacheContext<S, T>) cacheContextMap.getOrDefault(object, cacheContextFactory.create());
    }

}
