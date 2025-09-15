package com.checkaboy.deepcopy.filler.transaction;

import com.checkaboy.deepcopy.context.cache.ICache;
import com.checkaboy.deepcopy.context.factory.ICacheFactory;
import com.checkaboy.deepcopy.filler.model.interf.IFiller;

/**
 * @author Taras Shaptala
 */
public class FillingTransaction<S, T>
        implements IFillingTransaction<S, T> {

    private final IFiller<S, T> filler;
    private final ICacheFactory cacheFactory;

    public FillingTransaction(IFiller<S, T> filler, ICacheFactory cacheFactory) {
        this.filler = filler;
        this.cacheFactory = cacheFactory;
    }

    public void fill(S source, T target) {
        ICache cache = cacheFactory.create();
        filler.fill(cache, source, target);
    }

    public static <S, T> IFillingTransaction<S, T> nonCachedTransaction(IFiller<S, T> filler) {
        return new FillingTransaction<>(filler, () -> null);
    }

}
