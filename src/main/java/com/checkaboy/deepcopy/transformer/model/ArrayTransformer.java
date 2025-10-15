package com.checkaboy.deepcopy.transformer.model;

import com.checkaboy.deepcopy.context.cache.ICopyistCache;
import com.checkaboy.deepcopy.filler.model.general.ArrayFiller;
import com.checkaboy.deepcopy.filler.model.interf.IArrayFiller;
import com.checkaboy.deepcopy.transformer.model.interf.IArrayTransformer;

import java.util.function.Function;

/**
 * @author Taras Shaptala
 */
public class ArrayTransformer<S, T>
        implements IArrayTransformer<S, T> {

    private final Function<Integer, T[]> constructor;
    private final IArrayFiller<S, T> arrayFiller;

    public ArrayTransformer(Function<Integer, T[]> constructor, IArrayFiller<S, T> arrayFiller) {
        this.constructor = constructor;
        this.arrayFiller = arrayFiller;
    }

    @Override
    public T[] transform(ICopyistCache cache, S[] source) {
        if (source == null)
            return null;

        T[] targetCollection = constructor.apply(source.length);
        arrayFiller.fill(cache, source, targetCollection);

        return targetCollection;
    }

    public static <S> ArrayTransformer<S, S> primitiveCollectionTransformer(Function<Integer, S[]> constructor) {
        return new ArrayTransformer<>(constructor, ArrayFiller.primitiveArrayFiller());
    }

}
