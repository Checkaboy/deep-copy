package com.checkaboy.deepcopy.filler.builder.interf;

import com.checkaboy.deepcopy.transformer.interf.IFieldTransformer;

import java.util.Collection;
import java.util.function.Predicate;

/**
 * @author Taras Shaptala
 */
public interface ICollectionFillerBuilder<SC extends Collection<SV>, SV, TC extends Collection<TV>, TV>
        extends IFillerBuilder<SC, TC> {

    ICollectionFillerBuilder<SC, SV, TC, TV> setTransformer(IFieldTransformer<SV, TV> transformer);

    ICollectionFillerBuilder<SC, SV, TC, TV> setPredicate(Predicate<SV> predicate);

}
