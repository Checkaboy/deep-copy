package com.checkaboy.deepcopy.copyist;

import com.checkaboy.deepcopy.cloner.FieldCloner;
import com.checkaboy.deepcopy.cloner.interf.IFieldCloner;
import com.checkaboy.deepcopy.copyist.interf.ICollectionCopyist;

import java.util.Collection;

/**
 * @author Taras Shaptala
 */
public class CollectionCopyist<C extends Collection<V>, V>
        implements ICollectionCopyist<C, V> {

    private final IFieldCloner<V> fieldCloner;

    public CollectionCopyist(IFieldCloner<V> fieldCloner) {
        this.fieldCloner = fieldCloner;
    }

    @Override
    public void copy(C source, C target) {
        if (source != null) {
            if (!target.isEmpty()) target.clear();
            if (source.isEmpty()) return;

            source.forEach(o -> target.add(fieldCloner.clone(o)));
        }
    }

    public static <C extends Collection<V>, V> ICollectionCopyist<C, V> primitiveCollectionCopyist() {
        return new CollectionCopyist<>(FieldCloner.simpleFieldCloner());
    }

}
