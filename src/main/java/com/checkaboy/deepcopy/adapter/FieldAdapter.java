package com.checkaboy.deepcopy.adapter;

import com.checkaboy.deepcopy.ICopyist;
import com.checkaboy.deepcopy.adapter.interf.IFieldAdapter;
import com.checkaboy.deepcopy.cloner.FieldCloner;
import com.checkaboy.deepcopy.copyist.FieldCopyist;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author Taras Shaptala
 */
public class FieldAdapter<SO, TO, SV, TV>
        implements IFieldAdapter<SO, TO> {

    private final Function<SO, SV> extractor;
    private final BiConsumer<TO, TV> inserter;
    private final Supplier<TV> constructor;
    private final ICopyist<SV, TV> copyist;
//    private final ICloner<SV, TV> cloner;

    public FieldAdapter(Function<SO, SV> extractor, BiConsumer<TO, TV> inserter, Supplier<TV> constructor, ICopyist<SV, TV> copyist/*, ICloner<SV, TV> cloner*/) {
        this.extractor = extractor;
        this.inserter = inserter;
        this.constructor = constructor;
        this.copyist = copyist;
//        this.cloner = cloner;
    }

    @Override
    public void copy(SO source, TO target) {
        SV valueSource = extractor.apply(source);
        TV valueTarget = constructor.get();
        copyist.copy(valueSource, valueTarget);
        inserter.accept(target, valueTarget);
    }

//    public static <S, T, V> IFieldAdapter<S, T> simpleFieldAdapter(Function<S, V> extractor, BiConsumer<T, V> inserter) {
//        return new FieldAdapter<>(extractor, inserter, FieldCopyist.simpleFieldCopyist());
//    }

}
