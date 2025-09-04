package com.checkaboy.deepcopy.filler;

import com.checkaboy.deepcopy.filler.interf.IFieldFiller;
import com.checkaboy.deepcopy.transformer.FieldTransformer;
import com.checkaboy.deepcopy.transformer.interf.IFieldTransformer;

import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * @author Taras Shaptala
 */
public class FieldFiller<SO, TO, SV, TV>
        implements IFieldFiller<SO, TO> {

    private final Function<SO, SV> extractor;
    private final BiConsumer<TO, TV> inserter;
    private final IFieldTransformer<SV, TV> transformer;

    public FieldFiller(Function<SO, SV> extractor, BiConsumer<TO, TV> inserter, IFieldTransformer<SV, TV> transformer) {
        this.extractor = extractor;
        this.inserter = inserter;
        this.transformer = transformer;
    }

    public void fill(SO source, TO target) {
        SV valueSource = extractor.apply(source);
        TV valueTarget = transformer.transform(valueSource);
        inserter.accept(target, valueTarget);
    }

    public static <S, T, V> IFieldFiller<S, T> simpleFieldMapper(Function<S, V> extractor, BiConsumer<T, V> inserter) {
        return new FieldFiller<>(extractor, inserter, FieldTransformer.simpleFieldTransformer());
    }

}
