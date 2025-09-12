package com.checkaboy.deepcopy.filler.model.general;

import com.checkaboy.deepcopy.cache.ICacheContext;
import com.checkaboy.deepcopy.filler.model.abstr.AbstractFieldFiller;
import com.checkaboy.deepcopy.filler.model.interf.IFieldFiller;
import com.checkaboy.deepcopy.transformer.model.FieldTransformer;
import com.checkaboy.deepcopy.transformer.model.interf.IFieldTransformer;

import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * @author Taras Shaptala
 */
public class FieldFiller<SO, TO, SV, TV>
        extends AbstractFieldFiller<SO, TO, SV>
        implements IFieldFiller<SO, TO> {

    private final BiConsumer<TO, TV> inserter;
    private final IFieldTransformer<SV, TV> transformer;

    public FieldFiller(Function<SO, SV> extractor, BiConsumer<TO, TV> inserter, IFieldTransformer<SV, TV> transformer) {
        super(extractor);
        this.inserter = inserter;
        this.transformer = transformer;
    }

    @Override
    protected void fillValue(ICacheContext cacheContext, TO target, SV sourceValue) {
        TV valueTarget = transformer.transform(cacheContext, sourceValue);
        inserter.accept(target, valueTarget);
    }

    public static <S, T, V> IFieldFiller<S, T> simpleFieldFiller(Function<S, V> extractor, BiConsumer<T, V> inserter) {
        return new FieldFiller<>(extractor, inserter, FieldTransformer.simpleFieldTransformer());
    }

}
