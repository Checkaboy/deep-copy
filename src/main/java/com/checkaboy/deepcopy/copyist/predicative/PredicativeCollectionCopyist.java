package com.checkaboy.deepcopy.copyist.predicative;

import com.checkaboy.deepcopy.cloner.interf.IFieldCloner;
import com.checkaboy.deepcopy.copyist.based.CollectionCopyist;
import com.checkaboy.deepcopy.copyist.interf.ICollectionCopyist;

import java.util.Collection;
import java.util.function.Predicate;

/**
 * @author Taras Shaptala
 */
public class PredicativeCollectionCopyist<C extends Collection<V>, V>
        extends CollectionCopyist<C, V>
        implements ICollectionCopyist<C, V> {

    private final Predicate<V> predicate;

    public PredicativeCollectionCopyist(IFieldCloner<V> fieldCloner, Predicate<V> predicate) {
        super(fieldCloner);
        this.predicate = predicate;
    }

    @Override
    protected void copyValue(C target, V sourceValue) {
        if (predicate.test(sourceValue))
            super.copyValue(target, sourceValue);
    }

}
