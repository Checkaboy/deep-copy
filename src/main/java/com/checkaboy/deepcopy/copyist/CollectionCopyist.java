package com.checkaboy.deepcopy.copyist;

import com.checkaboy.deepcopy.copyist.interf.ICollectionCopyist;
import com.checkaboy.deepcopy.copyist.interf.ICopyist;

import java.util.Collection;

/**
 * @author Taras Shaptala
 */
public class CollectionCopyist<C extends Collection<V>, V>
        implements ICollectionCopyist<C, V> {

    protected final ICopyist<V> copyist;

    public CollectionCopyist(ICopyist<V> copyist) {
        this.copyist = copyist;
    }

    @Override
    public void copy(C source, C target) {
        if (source != null) {
            if (source.isEmpty()) return;

            source.forEach(v -> {

            });
        }
    }


}
