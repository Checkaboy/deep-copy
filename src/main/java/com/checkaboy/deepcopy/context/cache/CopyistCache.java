package com.checkaboy.deepcopy.context.cache;

import com.checkaboy.deepcopy.context.factory.ICopyistContextFactory;

import java.util.IdentityHashMap;
import java.util.Map;

/**
 * @author Taras Shaptala
 */
public class CopyistCache
        implements ICopyistCache {

    private final Map<Object, ICopyistContext<?, ?>> cacheContextMap;
    private final ICopyistContextFactory cacheContextFactory;

    public CopyistCache() {
        this(new IdentityHashMap<>(), CopyistContext::new);
    }

    public CopyistCache(ICopyistContextFactory cacheContextFactory) {
        this(new IdentityHashMap<>(), cacheContextFactory);
    }

    public CopyistCache(Map<Object, ICopyistContext<?, ?>> cacheContextMap) {
        this(cacheContextMap, CopyistContext::new);
    }

    public CopyistCache(Map<Object, ICopyistContext<?, ?>> cacheContextMap, ICopyistContextFactory cacheContextFactory) {
        this.cacheContextMap = cacheContextMap;
        this.cacheContextFactory = cacheContextFactory;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <S, T> ICopyistContext<S, T> get(Object object) {
        ICopyistContext<S, T> cacheContext = (ICopyistContext<S, T>) cacheContextMap.get(object);
        if (cacheContext == null) {
            cacheContext = cacheContextFactory.create();
            cacheContextMap.put(object, cacheContext);
        }
        return cacheContext;
    }

}
