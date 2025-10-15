package com.checkaboy.deepcopy.filler.builder;

import com.checkaboy.deepcopy.filler.model.interf.ICollectionFiller;
import com.checkaboy.deepcopy.transformer.model.interf.IFieldTransformer;

import java.util.List;
import java.util.function.Predicate;

/**
 * @author Taras Shaptala
 */
public class ListFillerBuilder<S, T>
        extends CollectionFillerBuilder<List<S>, S, List<T>, T> {

    protected ListFillerBuilder(Class<S> sourceType, Class<T> targetType) {
        super(sourceType, targetType);
    }

    @Override
    public ListFillerBuilder<S, T> setTransformer(IFieldTransformer<S, T> transformer) {
        super.setTransformer(transformer);
        return this;
    }

    @Override
    public ListFillerBuilder<S, T> setPredicate(Predicate<S> predicate) {
        super.setPredicate(predicate);
        return this;
    }

    @Override
    public ICollectionFiller<List<S>, S, List<T>, T> build() {
        return super.build();
    }

}
