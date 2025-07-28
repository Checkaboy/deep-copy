package com.checkaboy.deepcopy.cloner;

import com.checkaboy.deepcopy.cloner.interf.ICloner;
import com.checkaboy.deepcopy.cloner.interf.IMapCloner;
import com.checkaboy.deepcopy.copyist.MapCopyist;
import com.checkaboy.deepcopy.copyist.interf.IMapCopyist;

import java.util.Map;
import java.util.function.Function;

/**
 * @author Taras Shaptala
 */
public class MapCloner<M extends Map<K, V>, K, V>
        implements IMapCloner<M, K, V> {

    private final Function<Integer, M> constructor;
    private final IMapCopyist<M, K, V> copyist;

    public MapCloner(Function<Integer, M> constructor, IMapCopyist<M, K, V> copyist) {
        this.constructor = constructor;
        this.copyist = copyist;
    }

    @Override
    public M clone(M source) {
        if (source == null)
            return null;

        M newTargetMap = constructor.apply(source.size());
        copyist.copy(source, newTargetMap);

        return newTargetMap;
    }

    public static <M extends Map<K, V>, K, V> IMapCloner<M, K, V> primitiveKeyMapCloner(Function<Integer, M> constructor, ICloner<V> valueCloner) {
        return new MapCloner<>(constructor, MapCopyist.primitiveKeyMapCopyist(valueCloner));
    }

    public static <M extends Map<K, V>, K, V> IMapCloner<M, K, V> primitiveKeyAndValueMapCloner(Function<Integer, M> constructor) {
        return new MapCloner<>(constructor, MapCopyist.primitiveKeyAndValueMapCopyist());
    }

}
