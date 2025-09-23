package com.checkaboy.deepcopy.transformer.model.interf;

import com.checkaboy.deepcopy.context.cache.ICopyistCache;

/**
 * @author Taras Shaptala
 */
@FunctionalInterface
public interface ITransformer<S, T> {

    T transform(ICopyistCache cache, S source);

}
