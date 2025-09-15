package com.checkaboy.deepcopy.filler.model.interf;

import com.checkaboy.deepcopy.context.cache.ICache;

/**
 * @author Taras Shaptala
 */
@FunctionalInterface
public interface IFiller<S, T> {

    void fill(ICache cache, S source, T target);

}
