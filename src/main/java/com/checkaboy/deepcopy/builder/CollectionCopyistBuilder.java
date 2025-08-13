package com.checkaboy.deepcopy.builder;

import com.checkaboy.deepcopy.builder.interf.ICollectionCopyistBuilder;
import com.checkaboy.deepcopy.cloner.FieldCloner;
import com.checkaboy.deepcopy.cloner.interf.ICloner;
import com.checkaboy.deepcopy.copyist.CollectionCopyist;

import java.util.Collection;

/**
 * @author Taras Shaptala
 */
public class CollectionCopyistBuilder<C extends Collection<V>, V>
        implements ICollectionCopyistBuilder<V> {

    private final Class<V> type;
    private ICloner<V> cloner = FieldCloner.simpleCloner();

    public CollectionCopyistBuilder(Class<V> type) {
        this.type = type;
    }

    @Override
    public ICollectionCopyistBuilder<V> setCloner(ICloner<V> cloner) {
        this.cloner = cloner;
        return this;
    }

    public CollectionCopyist<C, V> build() {
        return new CollectionCopyist<C, V>(cloner);
    }

    public Class<V> getType() {
        return type;
    }

}
