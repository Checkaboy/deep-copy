package com.checkaboy.deepcopy.filler.builder.interf;

import com.checkaboy.deepcopy.transformer.model.interf.IFieldTransformer;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author Taras Shaptala
 */
public interface IFieldFillerBuilder<SO, TO, SV, TV>
        extends IFillerBuilder<SO, TO> {

    IFieldFillerBuilder<SO, TO, SV, TV> setExtractor(Function<SO, SV> extractor);

    IFieldFillerBuilder<SO, TO, SV, TV> setInserter(BiConsumer<TO, TV> inserter);

    IFieldFillerBuilder<SO, TO, SV, TV> setTransformer(IFieldTransformer<SV, TV> transformer);

    IFieldFillerBuilder<SO, TO, SV, TV> setPredicate(Predicate<SV> predicate);

}
