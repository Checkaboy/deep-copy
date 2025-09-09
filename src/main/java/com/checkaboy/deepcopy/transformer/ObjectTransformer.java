package com.checkaboy.deepcopy.transformer;

import com.checkaboy.deepcopy.cache.ICacheContext;
import com.checkaboy.deepcopy.cache.MapCacheContext;
import com.checkaboy.deepcopy.filler.interf.IObjectFiller;
import com.checkaboy.deepcopy.transformer.interf.IObjectTransformer;

import java.util.function.Supplier;

/**
 * @author Taras Shaptala
 */
public class ObjectTransformer<S, T>
        implements IObjectTransformer<S, T> {

    private final Supplier<T> constructor;
    private final IObjectFiller<S, T> objectFiller;
    private final ICacheContext cacheContext;

    public ObjectTransformer(Supplier<T> constructor, IObjectFiller<S, T> objectFiller) {
        this(constructor, objectFiller, null);
    }

    public ObjectTransformer(Supplier<T> constructor, IObjectFiller<S, T> objectFiller, ICacheContext cacheContext) {
        this.constructor = constructor;
        this.objectFiller = objectFiller;
        this.cacheContext = cacheContext;
    }

    @Override
    public T transform(S source) {
        if (source == null)
            return null;

        if (cacheContext == null) {
            T target = constructor.get();
            objectFiller.fill(source, target);
            return target;
        }

        T cached = cacheContext.get(source);
        if (cached != null) {
            return cached;
        }

        T target = constructor.get();
        cacheContext.put(source, target);

        objectFiller.fill(source, target);
        return target;
    }

    public static <S, T> IObjectTransformer<S, T> simpleObjectTransformerWithIdentityCache(Supplier<T> constructor, IObjectFiller<S, T> objectAdapter) {
        return new ObjectTransformer<>(constructor, objectAdapter, MapCacheContext.identityCache());
    }

}
