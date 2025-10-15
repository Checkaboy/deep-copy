package com.checkaboy.deepcopy.transformer.builder;

import com.checkaboy.deepcopy.filler.model.interf.IArrayFiller;
import com.checkaboy.deepcopy.transformer.builder.interf.IArrayTransformerBuilder;
import com.checkaboy.deepcopy.transformer.model.ArrayTransformer;
import com.checkaboy.deepcopy.transformer.model.interf.ITransformer;
import com.checkaboy.objectutils.container.AbstractBiTypifiedContainer;

import java.util.function.Function;

/**
 * @author Taras Shaptala
 */
public class ArrayTransformerBuilder<S, T>
        extends AbstractBiTypifiedContainer<S, T>
        implements IArrayTransformerBuilder<S, T> {

    private Function<Integer, T[]> constructor = integer -> null;
    private IArrayFiller<S, T> arrayFiller = (cache, source, target) -> {
    };

    protected ArrayTransformerBuilder(Class<S> sourceType, Class<T> targetType) {
        super(sourceType, targetType);
    }

    @Override
    public ArrayTransformerBuilder<S, T> setConstructor(Function<Integer, T[]> constructor) {
        this.constructor = constructor;
        return this;
    }

    @Override
    public ArrayTransformerBuilder<S, T> setArrayFiller(IArrayFiller<S, T> arrayFiller) {
        this.arrayFiller = arrayFiller;
        return this;
    }

    @Override
    public ITransformer<S[], T[]> build() {
        return new ArrayTransformer<>(constructor, arrayFiller);
    }

}
