package com.checkaboy.deepcopy.copyist;

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

    public FieldCopyist(Function<O, V> extractor, BiConsumer<O, V> inserter) {
        this.extractor = extractor;
        this.inserter = inserter;
    }

    @Override
    public void copy(O source, O target) {
        inserter.accept(target, extractor.apply(source));
    }

}
