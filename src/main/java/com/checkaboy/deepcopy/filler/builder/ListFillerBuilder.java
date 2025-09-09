package com.checkaboy.deepcopy.filler.builder;

import com.checkaboy.deepcopy.filler.interf.ICollectionFiller;
import com.checkaboy.deepcopy.transformer.interf.IFieldTransformer;

import java.util.List;
import java.util.function.Predicate;

/**
 * @author Taras Shaptala
 */
public class ListFillerBuilder<S, T>
        extends CollectionFillerBuilder<List<S>, S, List<T>, T> {

    protected ListFillerBuilder(Class<T> type) {
        super(type);
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
