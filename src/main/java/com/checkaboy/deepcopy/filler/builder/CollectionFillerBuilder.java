package com.checkaboy.deepcopy.filler.builder;

import com.checkaboy.deepcopy.filler.builder.interf.ICollectionFillerBuilder;
import com.checkaboy.deepcopy.filler.model.general.CollectionFiller;
import com.checkaboy.deepcopy.filler.model.interf.ICollectionFiller;
import com.checkaboy.deepcopy.filler.model.predicative.PredicativeCollectionFiller;
import com.checkaboy.deepcopy.transformer.model.interf.IFieldTransformer;
import com.checkaboy.objectutils.container.AbstractBiTypifiedContainer;

import java.util.Collection;
import java.util.function.Predicate;

/**
 * @author Taras Shaptala
 */
public class CollectionFillerBuilder<SC extends Collection<SV>, SV, TC extends Collection<TV>, TV>
        extends AbstractBiTypifiedContainer<SV, TV>
        implements ICollectionFillerBuilder<SC, SV, TC, TV> {

    private IFieldTransformer<SV, TV> transformer = (cache, source) -> null;
    private Predicate<SV> predicate;

    protected CollectionFillerBuilder(Class<SV> sourceType, Class<TV> targetType) {
        super(sourceType, targetType);
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
        if (predicate == null) return new CollectionFiller<>(transformer);
        else return new PredicativeCollectionFiller<>(transformer, predicate);
    }

}
