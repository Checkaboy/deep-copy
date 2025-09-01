package com.checkaboy.deepcopy.copyist;

import com.checkaboy.deepcopy.cloner.FieldCloner;
import com.checkaboy.deepcopy.cloner.interf.IFieldCloner;
import com.checkaboy.deepcopy.copyist.interf.IMapCopyist;

import java.util.Map;

/**
 * @author Taras Shaptala
 */
public class MapCopyist<M extends Map<K, V>, K, V>
        implements IMapCopyist<M, K, V> {

    private final IFieldCloner<K> keyCloner;
    private final IFieldCloner<V> valueCloner;

    public MapCopyist(IFieldCloner<K> keyCloner, IFieldCloner<V> valueCloner) {
        this.keyCloner = keyCloner;
        this.valueCloner = valueCloner;
    }

    @Override
    public void copy(M source, M target) {
        if (source != null) {
            if (!target.isEmpty()) target.clear();
            if (source.isEmpty()) return;

            source.forEach((k, v) -> target.put(keyCloner.clone(k), valueCloner.clone(v)));
        }
    }

    public static <M extends Map<K, V>, K, V> IMapCopyist<M, K, V> primitiveKeyMapCopyist(IFieldCloner<V> valueCloner) {
        return new MapCopyist<>(FieldCloner.simpleFieldCloner(), valueCloner);
    }

    public static <M extends Map<K, V>, K, V> IMapCopyist<M, K, V> primitiveKeyAndValueMapCopyist() {
        return new MapCopyist<>(FieldCloner.simpleFieldCloner(), FieldCloner.simpleFieldCloner());
    }

}
