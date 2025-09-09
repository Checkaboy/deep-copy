package com.checkaboy.deepcopy.copyist.based;

import com.checkaboy.deepcopy.cloner.FieldCloner;
import com.checkaboy.deepcopy.cloner.interf.IFieldCloner;
import com.checkaboy.deepcopy.copyist.AbstractMapCopyist;
import com.checkaboy.deepcopy.copyist.interf.IMapCopyist;

import java.util.Map;

/**
 * @author Taras Shaptala
 */
public class MapCopyist<M extends Map<K, V>, K, V>
        extends AbstractMapCopyist<M, K, V>
        implements IMapCopyist<M, K, V> {

    private final IFieldCloner<K> keyCloner;
    private final IFieldCloner<V> valueCloner;

    public MapCopyist(IFieldCloner<K> keyCloner, IFieldCloner<V> valueCloner) {
        this.keyCloner = keyCloner;
        this.valueCloner = valueCloner;
    }

    @Override
    protected void copyValue(M target, K key, V value) {
        target.put(keyCloner.clone(key), valueCloner.clone(value));
    }

    public static <M extends Map<K, V>, K, V> IMapCopyist<M, K, V> primitiveKeyMapCopyist(IFieldCloner<V> valueCloner) {
        return new MapCopyist<>(FieldCloner.simpleFieldCloner(), valueCloner);
    }

    public static <M extends Map<K, V>, K, V> IMapCopyist<M, K, V> primitiveKeyAndValueMapCopyist() {
        return new MapCopyist<>(FieldCloner.simpleFieldCloner(), FieldCloner.simpleFieldCloner());
    }

}
