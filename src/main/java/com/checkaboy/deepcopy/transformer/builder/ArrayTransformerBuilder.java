package com.checkaboy.deepcopy.transformer.builder;

import com.checkaboy.deepcopy.filler.model.interf.IArrayFiller;
import com.checkaboy.deepcopy.transformer.builder.interf.IArrayTransformerBuilder;
import com.checkaboy.deepcopy.transformer.model.ArrayTransformer;
import com.checkaboy.deepcopy.transformer.model.interf.ITransformer;
import com.checkaboy.objectutils.container.AbstractTypifiedContainer;

import java.util.function.Function;

/**
 * @author Taras Shaptala
 */
public class ArrayTransformerBuilder<S, T>
        extends AbstractTypifiedContainer<T>
        implements IArrayTransformerBuilder<S, T> {

    private Function<Integer, T[]> constructor;
    private IArrayFiller<S, T> arrayFiller;

    protected ArrayTransformerBuilder(Class<T> type) {
        super(type);
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
        if (constructor == null)
            throw new NullPointerException("ArrayTransformerBuilder<" + getType().getSimpleName() + "[]> can`t create " +
                    "ITransformer<" + getType().getSimpleName() + "[]> without constructor");

        if (arrayFiller == null)
            throw new NullPointerException("ArrayTransformerBuilder<" + getType().getSimpleName() + "[]> can`t create " +
                    "ITransformer<" + getType().getSimpleName() + "[]> without arrayFiller");

        return new ArrayTransformer<>(constructor, arrayFiller);
    }

}
