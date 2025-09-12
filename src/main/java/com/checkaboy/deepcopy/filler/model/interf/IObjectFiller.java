package com.checkaboy.deepcopy.filler.model.interf;

import com.checkaboy.deepcopy.cache.ICacheContext;

import java.util.Map;

/**
 * @author Taras Shaptala
 */
public interface IObjectFiller<S, T>
        extends IFieldFiller<S, T>, Map<String, IFieldFiller<S, T>> {

    void fieldFill(ICacheContext cacheContext, String fieldName, S source, T target);

}
