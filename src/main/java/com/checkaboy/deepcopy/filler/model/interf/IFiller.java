package com.checkaboy.deepcopy.filler.model.interf;

import com.checkaboy.deepcopy.context.cache.ICopyistCache;

/**
 * @author Taras Shaptala
 */
@FunctionalInterface
public interface IFiller<S, T> {

    void fill(ICopyistCache cache, S source, T target);

}
