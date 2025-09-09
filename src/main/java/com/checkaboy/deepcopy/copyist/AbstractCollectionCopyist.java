package com.checkaboy.deepcopy.copyist;

import com.checkaboy.deepcopy.copyist.interf.ICollectionCopyist;

import java.util.Collection;

/**
 * @author Taras Shaptala
 */
public abstract class AbstractCollectionCopyist<C extends Collection<V>, V>
        implements ICollectionCopyist<C, V> {

    @Override
    public void copy(C source, C target) {
        if (source != null) {
            if (!target.isEmpty()) target.clear();
            if (source.isEmpty()) return;
            source.forEach(v -> copyValue(target, v));
        }
    }

    protected abstract void copyValue(C target, V sourceValue);

}
