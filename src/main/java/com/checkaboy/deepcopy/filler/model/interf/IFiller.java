package com.checkaboy.deepcopy.filler.model.interf;

import com.checkaboy.deepcopy.cache.ICacheContext;

/**
 * @author Taras Shaptala
 */
@FunctionalInterface
public interface IFiller<S, T> {

    void fill(ICacheContext cacheContext, S source, T target);

}
