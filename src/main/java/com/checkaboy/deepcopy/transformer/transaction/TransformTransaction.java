package com.checkaboy.deepcopy.transformer.transaction;

import com.checkaboy.deepcopy.context.cache.ICache;
import com.checkaboy.deepcopy.context.factory.ICacheFactory;
import com.checkaboy.deepcopy.transformer.model.interf.ITransformer;

/**
 * @author Taras Shaptala
 */
public class TransformTransaction<S, T>
        implements ITransformTransaction<S, T> {

    private final ITransformer<S, T> transformer;
    private final ICacheFactory cacheFactory;

    public TransformTransaction(ITransformer<S, T> transformer, ICacheFactory cacheFactory) {
        this.transformer = transformer;
        this.cacheFactory = cacheFactory;
    }

    @Override
    public T transform(S source) {
        ICache cache = cacheFactory.create();
//        return transformer.transform(cache, source);
        return null;
    }

}
