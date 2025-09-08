package com.checkaboy.deepcopy.cache;

import java.util.IdentityHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author Taras Shaptala
 */
public class CacheContext<S, T, K>
        implements ICacheContext<S, T, K> {

    private final Map<K, T> cache;
    private final Function<S, K> keyExtractor;

    public CacheContext(Map<K, T> cache, Function<S, K> keyExtractor) {
        this.cache = cache;
        this.keyExtractor = keyExtractor;
    }

    @Override
    public T get(S source) {
        return cache.get(keyExtractor.apply(source));
    }

    @Override
    public void put(S source, T target) {
        cache.put(keyExtractor.apply(source), target);
    }

    @Override
    public T putIfAbsent(S source, Supplier<T> constructor) {
        return cache.computeIfAbsent(keyExtractor.apply(source), k -> constructor.get());
    }

    @Override
    public void drop() {
        cache.clear();
    }

    public static <S, T> ICacheContext<S, T, S> identityCache() {
        return new CacheContext<>(new IdentityHashMap<>(), s -> s);
    }

}
