package com.checkaboy.deepcopy.adapter;

import com.checkaboy.deepcopy.adapter.interf.IFieldAdapter;
import com.checkaboy.deepcopy.cloner.FieldCloner;
import com.checkaboy.deepcopy.cloner.interf.IFieldCloner;

import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * @author Taras Shaptala
 */
public class FieldAdapter<S, T, V>
        implements IFieldAdapter<S, T> {

    private final Function<S, V> extractor;
    private final BiConsumer<T, V> inserter;
    private final IFieldCloner<V> fieldCloner;

    public FieldAdapter(Function<S, V> extractor, BiConsumer<T, V> inserter, IFieldCloner<V> fieldCloner) {
        this.extractor = extractor;
        this.inserter = inserter;
        this.fieldCloner = fieldCloner;
    }

    @Override
    public void copy(S source, T target) {
        V sourceValue = extractor.apply(source);
        V targetValue = fieldCloner.clone(sourceValue);
        inserter.accept(target, targetValue);
    }

    public static <S, T, V> IFieldAdapter<S, T> simpleFieldCopyist(Function<S, V> extractor, BiConsumer<T, V> inserter) {
        return new FieldAdapter<>(extractor, inserter, FieldCloner.simpleFieldCloner());
    }

}
