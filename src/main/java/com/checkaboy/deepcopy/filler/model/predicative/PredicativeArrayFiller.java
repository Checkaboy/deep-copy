package com.checkaboy.deepcopy.filler.model.predicative;

import com.checkaboy.deepcopy.context.cache.ICopyistCache;
import com.checkaboy.deepcopy.filler.model.general.ArrayFiller;
import com.checkaboy.deepcopy.filler.model.interf.IArrayFiller;
import com.checkaboy.deepcopy.transformer.model.interf.IFieldTransformer;

import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author Taras Shaptala
 */
public class PredicativeArrayFiller<S, T>
        extends ArrayFiller<S, T>
        implements IArrayFiller<S, T> {

    private final Predicate<S> predicate;

    public PredicativeArrayFiller(Supplier<T> transformer, Predicate<S> predicate) {
        this((cacheContext, source) -> transformer.get(), predicate);
    }

    public PredicativeArrayFiller(IFieldTransformer<S, T> transformer, Predicate<S> predicate) {
        super(transformer);
        this.predicate = predicate;
    }

    @Override
    protected void fillValue(ICopyistCache cache, int index, T[] target, S sourceValue) {
        if (predicate.test(sourceValue))
            super.fillValue(cache, index, target, sourceValue);
    }

}
