package com.checkaboy.deepcopy.copyist;

import com.checkaboy.deepcopy.cloner.FieldCloner;
import com.checkaboy.deepcopy.cloner.interf.ICloner;
import com.checkaboy.deepcopy.copyist.interf.ICopyist;

import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * @author Taras Shaptala
 */
public class FieldCopyist<O, V>
        implements ICopyist<O> {

    private final Function<O, V> extractor;
    private final BiConsumer<O, V> inserter;
    private final ICloner<V> cloner;

    public FieldCopyist(Function<O, V> extractor, BiConsumer<O, V> inserter, ICloner<V> cloner) {
        this.extractor = extractor;
        this.inserter = inserter;
        this.cloner = cloner;
    }

    @Override
    public void copy(O source, O target) {
        V sourceValue = extractor.apply(source);
        V targetValue = cloner.clone(sourceValue);
        inserter.accept(target, targetValue);
    }

    public static <O, V> ICopyist<O> simpleCopyist(Function<O, V> extractor, BiConsumer<O, V> inserter) {
        return new FieldCopyist<>(extractor, inserter, FieldCloner.simpleCloner());
    }

}
