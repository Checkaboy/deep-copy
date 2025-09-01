package com.checkaboy.deepcopy.builder.interf;

import com.checkaboy.deepcopy.cloner.interf.IFieldCloner;

import java.util.Map;

/**
 * @author Taras Shaptala
 */
public interface IMapCopyistBuilder<M extends Map<K, V>, K, V> {
    IMapCopyistBuilder<M, K, V> setKeyCloner(IFieldCloner<K> keyCloner);

    IMapCopyistBuilder<M, K, V> setValueCloner(IFieldCloner<V> valueCloner);
}
