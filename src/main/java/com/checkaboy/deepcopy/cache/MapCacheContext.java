package com.checkaboy.deepcopy.cache;

import java.util.IdentityHashMap;
import java.util.Map;

/**
 * @author Taras Shaptala
 */
public class MapCacheContext
        implements ICacheContext {

    private final Map<Object, Object> delegate;

    public MapCacheContext(Map<Object, Object> delegate) {
        this.delegate = delegate;
    }

    @SuppressWarnings("unchecked")
    public <S, T> T get(S source) {
        return (T) delegate.get(source);
    }

    public <S, T> void put(S source, T target) {
        delegate.put(source, target);
    }

    @Override
    public void drop() {
        delegate.clear();
    }


    public static ICacheContext identityCache() {
        return new MapCacheContext(new IdentityHashMap<>());
    }

}
