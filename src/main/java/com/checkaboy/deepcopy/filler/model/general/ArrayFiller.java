package com.checkaboy.deepcopy.filler.model.general;

import com.checkaboy.deepcopy.context.cache.ICopyistCache;
import com.checkaboy.deepcopy.filler.model.abstr.AbstractArrayFiller;
import com.checkaboy.deepcopy.filler.model.interf.IArrayFiller;
import com.checkaboy.deepcopy.transformer.model.interf.IFieldTransformer;

import java.util.function.Supplier;

/**
 * @author Taras Shaptala
 */
public class ArrayFiller<S, T>
        extends AbstractArrayFiller<S, T>
        implements IArrayFiller<S, T> {

    private final IFieldTransformer<S, T> transformer;

    public ArrayFiller(Supplier<T> transformer) {
        this((cacheContext, source) -> transformer.get());
    }

    public ArrayFiller(IFieldTransformer<S, T> transformer) {
        this.transformer = transformer;
    }

    @Override
    protected void fillValue(ICopyistCache cache, int index, T[] target, S sourceValue) {
        target[index] = transformer.transform(cache, sourceValue);
    }

    public static <S> IArrayFiller<S, S> primitiveArrayFiller() {
        return new ArrayFiller<>((cacheContext, source) -> source);
    }

}
