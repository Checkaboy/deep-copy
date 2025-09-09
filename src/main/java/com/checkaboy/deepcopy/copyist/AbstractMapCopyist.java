package com.checkaboy.deepcopy.copyist;

import com.checkaboy.deepcopy.copyist.interf.IMapCopyist;

import java.util.Map;

/**
 * @author Taras Shaptala
 */
public abstract class AbstractMapCopyist<M extends Map<K, V>, K, V>
        implements IMapCopyist<M, K, V> {

    @Override
    public void copy(M source, M target) {
        if (source != null) {
            if (!target.isEmpty()) target.clear();
            if (source.isEmpty()) return;
            source.forEach((k, v) -> copyValue(source, k, v));
        }
    }

    protected abstract void copyValue(M target, K key, V value);

}
