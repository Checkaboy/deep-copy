package com.checkaboy.deepcopy.copyist;

import com.checkaboy.deepcopy.cloner.FieldCloner;
import com.checkaboy.deepcopy.cloner.interf.ICloner;
import com.checkaboy.deepcopy.copyist.interf.ICollectionCopyist;

import java.util.Collection;

/**
 * @author Taras Shaptala
 */
public class CollectionCopyist<C extends Collection<V>, V>
        implements ICollectionCopyist<C, V> {

    private final ICloner<V> cloner;

    public CollectionCopyist(ICloner<V> cloner) {
        this.cloner = cloner;
    }

    @Override
    public void copy(C source, C target) {
        if (source != null) {
            if (!target.isEmpty()) target.clear();
            if (source.isEmpty()) return;

            source.forEach(o -> target.add(cloner.clone(o)));
        }
    }

    public static <C extends Collection<V>, V> ICollectionCopyist<C, V> primitiveCollectionCopyist() {
        return new CollectionCopyist<>(FieldCloner.simpleCloner());
    }

}
