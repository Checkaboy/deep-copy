package com.checkaboy.deepcopy.filler.predicative;

import com.checkaboy.deepcopy.filler.based.CollectionFiller;
import com.checkaboy.deepcopy.filler.interf.ICollectionFiller;
import com.checkaboy.deepcopy.transformer.interf.IFieldTransformer;

import java.util.Collection;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author Taras Shaptala
 */
public class PredicativeCollectionFiller<SC extends Collection<SV>, SV, TC extends Collection<TV>, TV>
        extends CollectionFiller<SC, SV, TC, TV>
        implements ICollectionFiller<SC, SV, TC, TV> {

    private final Predicate<SV> predicate;

    public PredicativeCollectionFiller(Supplier<TV> transformer, Predicate<SV> predicate) {
        super(transformer);
        this.predicate = predicate;
    }

    public PredicativeCollectionFiller(IFieldTransformer<SV, TV> transformer, Predicate<SV> predicate) {
        super(transformer);
        this.predicate = predicate;
    }

    @Override
    protected void fillValue(TC target, SV sourceValue) {
        if (predicate.test(sourceValue))
            super.fillValue(target, sourceValue);
    }

}
