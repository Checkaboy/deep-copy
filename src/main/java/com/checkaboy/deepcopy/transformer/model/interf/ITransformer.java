package com.checkaboy.deepcopy.transformer.model.interf;

import com.checkaboy.deepcopy.context.cache.ICache;

/**
 * @author Taras Shaptala
 */
@FunctionalInterface
public interface ITransformer<S, T> {

    T transform(ICache cache, S source);

}
