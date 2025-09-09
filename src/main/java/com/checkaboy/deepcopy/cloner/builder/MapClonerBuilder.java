package com.checkaboy.deepcopy.cloner.builder;

import com.checkaboy.deepcopy.cloner.MapCloner;
import com.checkaboy.deepcopy.cloner.builder.interf.IMapClonerBuilder;
import com.checkaboy.deepcopy.cloner.interf.IMapCloner;
import com.checkaboy.deepcopy.copyist.based.MapCopyist;
import com.checkaboy.deepcopy.copyist.interf.IMapCopyist;
import com.checkaboy.objectutils.container.AbstractTypifiedContainer;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author Taras Shaptala
 */
public class MapClonerBuilder<K, V>
        extends AbstractTypifiedContainer<V>
        implements IMapClonerBuilder<Map<K, V>, K, V> {

    private Function<Integer, Map<K, V>> constructor = HashMap::new;
    private IMapCopyist<Map<K, V>, K, V> mapCopyist = MapCopyist.primitiveKeyAndValueMapCopyist();

    protected MapClonerBuilder(Class<V> type) {
        super(type);
    }

    @Override
    public MapClonerBuilder<K, V> setConstructor(Function<Integer, Map<K, V>> constructor) {
        this.constructor = constructor;
        return this;
    }

    @Override
    public MapClonerBuilder<K, V> setMapCopyist(IMapCopyist<Map<K, V>, K, V> mapCopyist) {
        this.mapCopyist = mapCopyist;
        return this;
    }

    @Override
    public IMapCloner<Map<K, V>, K, V> build() {
        return new MapCloner<>(constructor, mapCopyist);
    }

}
