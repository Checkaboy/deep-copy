package com.checkaboy.deepcopy.copyist;

import com.checkaboy.deepcopy.copyist.interf.ICopyist;
import com.checkaboy.deepcopy.copyist.interf.IObjectCopyist;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author Taras Shaptala
 */
public class SubObjectCopyist<O, V>
        implements ICopyist<O> {

    private final Function<O, V> extractor;
    private final BiConsumer<O, V> inserter;
    private final Supplier<V> subObjectConstructor;
    private final IObjectCopyist<V> copyist;

    public SubObjectCopyist(Function<O, V> extractor, BiConsumer<O, V> inserter, Supplier<V> subObjectConstructor, IObjectCopyist<V> copyist) {
        this.extractor = extractor;
        this.inserter = inserter;
        this.subObjectConstructor = subObjectConstructor;
        this.copyist = copyist;
    }

    @Override
    public void copy(O source, O target) {
        V newTargetValue = subObjectConstructor.get();
        copyist.copy(extractor.apply(source), newTargetValue);
        inserter.accept(target, newTargetValue);
    }

}
