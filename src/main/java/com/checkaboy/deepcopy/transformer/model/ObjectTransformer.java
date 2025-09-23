package com.checkaboy.deepcopy.transformer.model;

import com.checkaboy.deepcopy.context.cache.ICopyistCache;
import com.checkaboy.deepcopy.context.cache.ICopyistContext;
import com.checkaboy.deepcopy.filler.model.interf.IObjectFiller;
import com.checkaboy.deepcopy.transformer.model.interf.IObjectTransformer;

import java.util.function.Supplier;

/**
 * @author Taras Shaptala
 */
public class ObjectTransformer<S, T>
        implements IObjectTransformer<S, T> {

    private final Supplier<T> constructor;
    private final IObjectFiller<S, T> objectFiller;

    public ObjectTransformer(Supplier<T> constructor, IObjectFiller<S, T> objectFiller) {
        this.constructor = constructor;
        this.objectFiller = objectFiller;
    }

    @Override
    public T transform(ICopyistCache cache, S source) {
        if (source == null)
            return null;

        if (cache == null) {
            T target = constructor.get();
            objectFiller.fill(null, source, target);
            return target;
        }

        ICopyistContext<S, T> cacheContext = cache.get(this);
        T cached = cacheContext.get(source);
        if (cached != null) return cached;

        T target = constructor.get();
        cacheContext.put(source, target);

        objectFiller.fill(cache, source, target);
        return target;
    }

}
