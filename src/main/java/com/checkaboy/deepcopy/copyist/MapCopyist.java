package com.checkaboy.deepcopy.copyist;

import com.checkaboy.deepcopy.cloner.FieldCloner;
import com.checkaboy.deepcopy.cloner.interf.ICloner;
import com.checkaboy.deepcopy.copyist.interf.IMapCopyist;

import java.util.Map;

/**
 * @author Taras Shaptala
 */
public class MapCopyist<M extends Map<K, V>, K, V>
        implements IMapCopyist<M, K, V> {

    private final ICloner<K> keyCloner;
    private final ICloner<V> valueCloner;

    public MapCopyist(ICloner<K> keyCloner, ICloner<V> valueCloner) {
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

    public static <M extends Map<K, V>, K, V> IMapCopyist<M, K, V> primitiveKeyMapCopyist(ICloner<V> valueCloner) {
        return new MapCopyist<>(FieldCloner.simpleCloner(), valueCloner);
    }

    public static <M extends Map<K, V>, K, V> IMapCopyist<M, K, V> primitiveKeyAndValueMapCopyist() {
        return new MapCopyist<>(FieldCloner.simpleCloner(), FieldCloner.simpleCloner());
    }

}
