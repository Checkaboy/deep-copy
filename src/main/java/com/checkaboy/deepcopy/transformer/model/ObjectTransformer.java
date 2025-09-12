package com.checkaboy.deepcopy.transformer.model;

import com.checkaboy.deepcopy.cache.ICacheContext;
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
    public T transform(ICacheContext cacheContext, S source) {
        if (source == null)
            return null;

        if (cacheContext == null) {
            T target = constructor.get();
            objectFiller.fill(cacheContext, source, target);
            return target;
        }

        T cached = cacheContext.get(source);
        if (cached != null) return cached;

        T target = constructor.get();
        cacheContext.put(source, target);

        objectFiller.fill(cacheContext, source, target);
        return target;
    }

}
