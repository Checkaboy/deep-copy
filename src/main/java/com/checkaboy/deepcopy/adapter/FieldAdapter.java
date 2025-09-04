package com.checkaboy.deepcopy.adapter;

import com.checkaboy.deepcopy.adapter.interf.IFieldAdapter;
import com.checkaboy.deepcopy.transformer.FieldTransformer;
import com.checkaboy.deepcopy.transformer.interf.IFieldTransformer;

import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * @author Taras Shaptala
 */
public class FieldAdapter<SO, TO, SV, TV>
        implements IFieldAdapter<SO, TO> {

    private final Function<SO, SV> extractor;
    private final BiConsumer<TO, TV> inserter;
    private final IFieldTransformer<SV, TV> transformer;

    public FieldAdapter(Function<SO, SV> extractor, BiConsumer<TO, TV> inserter, IFieldTransformer<SV, TV> transformer) {
        this.extractor = extractor;
        this.inserter = inserter;
        this.transformer = transformer;
    }

    public void copy(SO source, TO target) {
        SV valueSource = extractor.apply(source);
        TV valueTarget = transformer.clone(valueSource);
        inserter.accept(target, valueTarget);
    }

    public static <S, T, V> IFieldAdapter<S, T> simpleFieldAdapter(Function<S, V> extractor, BiConsumer<T, V> inserter) {
        return new FieldAdapter<>(extractor, inserter, FieldTransformer.simpleFieldTransformer());
    }

}
