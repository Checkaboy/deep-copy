package com.checkaboy.deepcopy.filler.builder;

import com.checkaboy.deepcopy.filler.builder.interf.IArrayFillerBuilder;
import com.checkaboy.deepcopy.filler.model.general.ArrayFiller;
import com.checkaboy.deepcopy.filler.model.interf.IArrayFiller;
import com.checkaboy.deepcopy.filler.model.predicative.PredicativeArrayFiller;
import com.checkaboy.deepcopy.transformer.model.interf.IFieldTransformer;
import com.checkaboy.objectutils.container.AbstractBiTypifiedContainer;

import java.util.function.Predicate;

/**
 * @author Taras Shaptala
 */
public class ArrayFillerBuilder<S, T>
        extends AbstractBiTypifiedContainer<S, T>
        implements IArrayFillerBuilder<S, T> {

    private IFieldTransformer<S, T> transformer = (cache, source) -> null;
    private Predicate<S> predicate;

    protected ArrayFillerBuilder(Class<S> sourceType, Class<T> targetType) {
        super(sourceType, targetType);
    }

    @Override
    public ArrayFillerBuilder<S, T> setTransformer(IFieldTransformer<S, T> transformer) {
        this.transformer = transformer;
        return this;
    }

    @Override
    public ArrayFillerBuilder<S, T> setPredicate(Predicate<S> predicate) {
        this.predicate = predicate;
        return this;
    }

    @Override
    public IArrayFiller<S, T> build() {
        if (predicate == null) return new ArrayFiller<>(transformer);
        else return new PredicativeArrayFiller<>(transformer, predicate);
    }

}
