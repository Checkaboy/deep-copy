package com.checkaboy.deepcopy.context.cache;

import com.checkaboy.deepcopy.context.factory.ICacheContextFactory;

import java.util.IdentityHashMap;
import java.util.Map;

/**
 * @author Taras Shaptala
 */
public class Cache
        implements ICache {

    private final Map<Object, ICacheContext<?, ?>> cacheContextMap;
    private final ICacheContextFactory cacheContextFactory;

    public Cache() {
        this(new IdentityHashMap<>(), CacheContext::new);
    }

    public Cache(ICacheContextFactory cacheContextFactory) {
        this(new IdentityHashMap<>(), cacheContextFactory);
    }

    public Cache(Map<Object, ICacheContext<?, ?>> cacheContextMap) {
        this(cacheContextMap, CacheContext::new);
    }

    public Cache(Map<Object, ICacheContext<?, ?>> cacheContextMap, ICacheContextFactory cacheContextFactory) {
        this.cacheContextMap = cacheContextMap;
        this.cacheContextFactory = cacheContextFactory;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <S, T> ICacheContext<S, T> get(Object object) {
        ICacheContext<S, T> cacheContext = (ICacheContext<S, T>) cacheContextMap.get(object);
        if (cacheContext == null) {
            cacheContext = cacheContextFactory.create();
            cacheContextMap.put(object, cacheContext);
        }
        return cacheContext;
    }

}
