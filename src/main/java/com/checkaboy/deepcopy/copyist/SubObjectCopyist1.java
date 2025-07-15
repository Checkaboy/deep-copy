package com.checkaboy.deepcopy.copyist;

import com.checkaboy.deepcopy.copyist.interf.ICopyist;
import com.checkaboy.deepcopy.copyist.interf.IObjectCopyist;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author Taras Shaptala
 */
public class SubObjectCopyist1<O, V>
        implements ICopyist<O> {

    private final Function<O, V> extractor;
    private final BiConsumer<O, V> inserter;
    private final IObjectCopyist<V> copyist;

    public SubObjectCopyist1(Function<O, V> extractor, BiConsumer<O, V> inserter, IObjectCopyist<V> copyist) {
        this.extractor = extractor;
        this.inserter = inserter;
        this.copyist = copyist;
    }

    @Override
    public void copy(O source, O target) {
        V targetValue = extractor.apply(target);
        copyist.copy(extractor.apply(source), targetValue);
        inserter.accept(target, targetValue);
    }

}
