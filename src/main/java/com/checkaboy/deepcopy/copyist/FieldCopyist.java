package com.checkaboy.deepcopy.copyist;

import com.checkaboy.deepcopy.cloner.FieldCloner;
import com.checkaboy.deepcopy.cloner.interf.IFieldCloner;
import com.checkaboy.deepcopy.copyist.interf.IFieldCopyist;

import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * @author Taras Shaptala
 */
public class FieldCopyist<O, V>
        implements IFieldCopyist<O> {

    private final Function<O, V> extractor;
    private final BiConsumer<O, V> inserter;
    private final IFieldCloner<V> fieldCloner;

    public FieldCopyist(Function<O, V> extractor, BiConsumer<O, V> inserter, IFieldCloner<V> fieldCloner) {
        this.extractor = extractor;
        this.inserter = inserter;
        this.fieldCloner = fieldCloner;
    }

    @Override
    public void copy(O source, O target) {
        V sourceValue = extractor.apply(source);
        V targetValue = fieldCloner.clone(sourceValue);
        inserter.accept(target, targetValue);
    }

    public static <O, V> IFieldCopyist<O> simpleFieldCopyist(Function<O, V> extractor, BiConsumer<O, V> inserter) {
        return new FieldCopyist<>(extractor, inserter, FieldCloner.simpleFieldCloner());
    }

}
