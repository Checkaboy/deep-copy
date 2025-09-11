package com.checkaboy.deepcopy.transformer;

import com.checkaboy.deepcopy.cache.ICacheContext;
import com.checkaboy.deepcopy.cache.MapCacheContext;
import com.checkaboy.deepcopy.filler.interf.IObjectFiller;
import com.checkaboy.deepcopy.transformer.interf.IObjectTransformer;

import java.util.function.Supplier;

/**
 * @author Taras Shaptala
 */
public class CachedObjectTransformer<S, T>
        extends ObjectTransformer<S, T>
        implements IObjectTransformer<S, T> {

    private final ICacheContext cacheContext;

    public CachedObjectTransformer(Supplier<T> constructor, IObjectFiller<S, T> objectFiller, ICacheContext cacheContext) {
        super(constructor, objectFiller);
        this.cacheContext = cacheContext;
    }

    @Override
    public T transform(S source) {
        if (source == null) return null;

        T cached = cacheContext.get(source);
        if (cached != null) return cached;

        T target = create();
        cacheContext.put(source, target);

        fill(source, target);
        return target;
    }

    public static <S, T> IObjectTransformer<S, T> simpleCachedObjectTransformerWithIdentityCache(Supplier<T> constructor, IObjectFiller<S, T> objectAdapter) {
        return new CachedObjectTransformer<>(constructor, objectAdapter, MapCacheContext.identityCache());
    }

}
