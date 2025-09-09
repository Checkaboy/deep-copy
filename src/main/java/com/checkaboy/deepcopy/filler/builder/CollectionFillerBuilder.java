package com.checkaboy.deepcopy.filler.builder;

import com.checkaboy.deepcopy.filler.based.CollectionFiller;
import com.checkaboy.deepcopy.filler.builder.interf.ICollectionFillerBuilder;
import com.checkaboy.deepcopy.filler.interf.ICollectionFiller;
import com.checkaboy.deepcopy.filler.predicative.PredicativeCollectionFiller;
import com.checkaboy.deepcopy.transformer.interf.IFieldTransformer;
import com.checkaboy.objectutils.container.AbstractTypifiedContainer;

import java.util.Collection;
import java.util.function.Predicate;

/**
 * @author Taras Shaptala
 */
public class CollectionFillerBuilder<SC extends Collection<SV>, SV, TC extends Collection<TV>, TV>
        extends AbstractTypifiedContainer<TV>
        implements ICollectionFillerBuilder<SC, SV, TC, TV> {

    private IFieldTransformer<SV, TV> transformer;
    private Predicate<SV> predicate;

    protected CollectionFillerBuilder(Class<TV> type) {
        super(type);
    }

    @Override
    public CollectionFillerBuilder<SC, SV, TC, TV> setTransformer(IFieldTransformer<SV, TV> transformer) {
        this.transformer = transformer;
        return this;
    }

    @Override
    public CollectionFillerBuilder<SC, SV, TC, TV> setPredicate(Predicate<SV> predicate) {
        this.predicate = predicate;
        return this;
    }

    @Override
    public ICollectionFiller<SC, SV, TC, TV> build() {
        if (transformer == null)
            throw new NullPointerException("CollectionFillerBuilder<" + getType().getSimpleName() + "> can`t create " +
                    "ICollectionFiller<" + getType().getSimpleName() + "> without transformer");

        if (predicate == null) return new CollectionFiller<>(transformer);
        else return new PredicativeCollectionFiller<>(transformer, predicate);
    }

}
