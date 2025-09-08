package com.checkaboy.deepcopy.filler;

import com.checkaboy.deepcopy.filler.interf.IFieldFiller;
import com.checkaboy.deepcopy.transformer.FieldTransformer;
import com.checkaboy.deepcopy.transformer.interf.IFieldTransformer;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author Taras Shaptala
 */
public class FieldFiller<SO, TO, SV, TV>
        implements IFieldFiller<SO, TO> {

    private final Function<SO, SV> extractor;
    private final BiConsumer<TO, TV> inserter;
    private final IFieldTransformer<SV, TV> transformer;
    private final Predicate<SV> predicate;

    public FieldFiller(Function<SO, SV> extractor, BiConsumer<TO, TV> inserter, IFieldTransformer<SV, TV> transformer) {
        this(extractor, inserter, transformer, null);
    }

    public FieldFiller(Function<SO, SV> extractor, BiConsumer<TO, TV> inserter, IFieldTransformer<SV, TV> transformer, Predicate<SV> predicate) {
        this.extractor = extractor;
        this.inserter = inserter;
        this.transformer = transformer;
        this.predicate = predicate;
    }

    public void fill(SO source, TO target) {
        SV valueSource = extractor.apply(source);
        if (predicate != null && predicate.test(valueSource)) {
            TV valueTarget = transformer.transform(valueSource);
            inserter.accept(target, valueTarget);
        }
    }

    public static <S, T, V> IFieldFiller<S, T> simpleFieldMapper(Function<S, V> extractor, BiConsumer<T, V> inserter) {
        return new FieldFiller<>(extractor, inserter, FieldTransformer.simpleFieldTransformer());
    }

}
