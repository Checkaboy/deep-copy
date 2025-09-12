package com.checkaboy.deepcopy.transformer.model.interf;

import com.checkaboy.deepcopy.cache.ICacheContext;

/**
 * @author Taras Shaptala
 */
@FunctionalInterface
public interface ITransformer<S, T> {

    T transform(ICacheContext cacheContext, S source);

}
