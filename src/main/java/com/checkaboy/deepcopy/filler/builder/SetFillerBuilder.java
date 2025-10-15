package com.checkaboy.deepcopy.filler.builder;

import com.checkaboy.deepcopy.filler.model.interf.ICollectionFiller;
import com.checkaboy.deepcopy.transformer.model.interf.IFieldTransformer;

import java.util.Set;
import java.util.function.Predicate;

/**
 * @author Taras Shaptala
 */
public class SetFillerBuilder<S, T>
        extends CollectionFillerBuilder<Set<S>, S, Set<T>, T> {

    public SetFillerBuilder(Class<S> sourceType, Class<T> targetType) {
        super(sourceType, targetType);
    }

    @Override
    public SetFillerBuilder<S, T> setTransformer(IFieldTransformer<S, T> transformer) {
        super.setTransformer(transformer);
        return this;
    }

    @Override
    public SetFillerBuilder<S, T> setPredicate(Predicate<S> predicate) {
        super.setPredicate(predicate);
        return this;
    }

    @Override
    public ICollectionFiller<Set<S>, S, Set<T>, T> build() {
        return super.build();
    }

}
