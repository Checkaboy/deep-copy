package com.checkaboy.deepcopy.cache.builder;

import java.util.Map;
import java.util.function.Function;

/**
 * @author Taras Shaptala
 */
public interface ICacheContextBuilder<S, T, K> {

    ICacheContextBuilder<S, T, K> setCache(Map<K, T> cache);

    ICacheContextBuilder<S, T, K> setKeyExtractor(Function<S, K> keyExtractor);

}
