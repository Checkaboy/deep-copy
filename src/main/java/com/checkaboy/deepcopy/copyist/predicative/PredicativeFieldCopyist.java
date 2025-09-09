package com.checkaboy.deepcopy.copyist.predicative;

import com.checkaboy.deepcopy.cloner.interf.IFieldCloner;
import com.checkaboy.deepcopy.copyist.based.FieldCopyist;
import com.checkaboy.deepcopy.copyist.interf.IFieldCopyist;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author Taras Shaptala
 */
public class PredicativeFieldCopyist<O, V>
        extends FieldCopyist<O, V>
        implements IFieldCopyist<O> {

    private final Predicate<V> predicate;

    public PredicativeFieldCopyist(Function<O, V> extractor, BiConsumer<O, V> inserter, IFieldCloner<V> fieldCloner, Predicate<V> predicate) {
        super(extractor, inserter, fieldCloner);
        this.predicate = predicate;
    }

    @Override
    protected void copyValue(O target, V sourceValue) {
        if (predicate.test(sourceValue)) {
            super.copyValue(target, sourceValue);
        }
    }

}
