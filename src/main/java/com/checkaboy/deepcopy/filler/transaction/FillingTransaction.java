package com.checkaboy.deepcopy.filler.transaction;

import com.checkaboy.deepcopy.context.cache.ICopyistCache;
import com.checkaboy.deepcopy.context.factory.ICopyistCacheFactory;
import com.checkaboy.deepcopy.filler.model.interf.IFiller;

/**
 * @author Taras Shaptala
 */
public class FillingTransaction<S, T>
        implements IFillingTransaction<S, T> {

    private final IFiller<S, T> filler;
    private final ICopyistCacheFactory cacheFactory;

    public FillingTransaction(IFiller<S, T> filler, ICopyistCacheFactory cacheFactory) {
        this.filler = filler;
        this.cacheFactory = cacheFactory;
    }

    public void fill(S source, T target) {
        ICopyistCache cache = cacheFactory.create();
        filler.fill(cache, source, target);
    }

    public static <S, T> IFillingTransaction<S, T> nonCachedTransaction(IFiller<S, T> filler) {
        return new FillingTransaction<>(filler, () -> null);
    }

}
