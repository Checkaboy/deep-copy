package com.checkaboy.deepcopy.builder;

import com.checkaboy.deepcopy.builder.interf.IMapCopyistBuilder;
import com.checkaboy.deepcopy.cloner.interf.IFieldCloner;
import com.checkaboy.objectutils.container.AbstractTypifiedContainer;
import com.checkaboy.deepcopy.copyist.MapCopyist;
import com.checkaboy.deepcopy.copyist.interf.IMapCopyist;

import java.util.Map;

/**
 * @author Taras Shaptala
 */
public class MapCopyistBuilder<M extends Map<K, V>, K, V>
        extends AbstractTypifiedContainer<V>
        implements IMapCopyistBuilder<M, K, V> {

    private IFieldCloner<K> keyCloner;
    private IFieldCloner<V> valueCloner;

    protected MapCopyistBuilder(Class<V> type) {
        super(type);
    }

    @Override
    public MapCopyistBuilder<M, K, V> setKeyCloner(IFieldCloner<K> keyCloner) {
        this.keyCloner = keyCloner;
        return this;
    }

    @Override
    public MapCopyistBuilder<M, K, V> setValueCloner(IFieldCloner<V> valueCloner) {
        this.valueCloner = valueCloner;
        return this;
    }

    public IMapCopyist<M, K, V> build() {
        return new MapCopyist<>(keyCloner, valueCloner);
    }

}
