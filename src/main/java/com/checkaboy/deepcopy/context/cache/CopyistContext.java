package com.checkaboy.deepcopy.context.cache;

import java.util.IdentityHashMap;
import java.util.Map;

/**
 * @author Taras Shaptala
 */
public class CopyistContext<S, T>
        implements ICopyistContext<S, T> {

    private final Map<S, T> map = new IdentityHashMap<>();

    @Override
    public T get(S source) {
        return map.get(source);
    }

    @Override
    public void put(S source, T target) {
        map.put(source, target);
    }

}
