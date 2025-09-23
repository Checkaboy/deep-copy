package com.checkaboy.deepcopy.transformer.transaction;

import com.checkaboy.deepcopy.context.cache.ICopyistCache;
import com.checkaboy.deepcopy.context.factory.ICopyistCacheFactory;
import com.checkaboy.deepcopy.transformer.model.interf.ITransformer;

/**
 * @author Taras Shaptala
 */
public class TransformationTransaction<S, T>
        implements ITransformationTransaction<S, T> {

    private final ITransformer<S, T> transformer;
    private final ICopyistCacheFactory cacheFactory;

    public TransformationTransaction(ITransformer<S, T> transformer, ICopyistCacheFactory cacheFactory) {
        this.transformer = transformer;
        this.cacheFactory = cacheFactory;
    }

    @Override
    public T transform(S source) {
        ICopyistCache cache = cacheFactory.create();
        return transformer.transform(cache, source);
    }

    public static <S, T> ITransformationTransaction<S, T> nonCachedTransaction(ITransformer<S, T> transformer) {
        return new TransformationTransaction<>(transformer, () -> null);
    }

}
