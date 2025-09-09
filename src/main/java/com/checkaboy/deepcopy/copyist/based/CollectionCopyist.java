package com.checkaboy.deepcopy.copyist.based;

import com.checkaboy.deepcopy.cloner.FieldCloner;
import com.checkaboy.deepcopy.cloner.interf.IFieldCloner;
import com.checkaboy.deepcopy.copyist.AbstractCollectionCopyist;
import com.checkaboy.deepcopy.copyist.interf.ICollectionCopyist;

import java.util.Collection;

/**
 * @author Taras Shaptala
 */
public class CollectionCopyist<C extends Collection<V>, V>
        extends AbstractCollectionCopyist<C, V>
        implements ICollectionCopyist<C, V> {

    private final IFieldCloner<V> fieldCloner;

    public CollectionCopyist(IFieldCloner<V> fieldCloner) {
        this.fieldCloner = fieldCloner;
    }

    @Override
    protected void copyValue(C target, V sourceValue) {
        target.add(fieldCloner.clone(sourceValue));
    }

    public static <C extends Collection<V>, V> ICollectionCopyist<C, V> primitiveCollectionCopyist() {
        return new CollectionCopyist<>(FieldCloner.simpleFieldCloner());
    }

}
