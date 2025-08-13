package com.checkaboy.deepcopy.builder;

import com.checkaboy.deepcopy.cloner.interf.ICloner;
import com.checkaboy.deepcopy.copyist.CollectionCopyist;

import java.util.Set;

/**
 * @author Taras Shaptala
 */
public class SetCopyistBuilder<V>
        extends CollectionCopyistBuilder<Set<V>, V> {

    public SetCopyistBuilder(Class<V> type) {
        super(type);
    }

    @Override
    public SetCopyistBuilder<V> setCloner(ICloner<V> cloner) {
        super.setCloner(cloner);
        return this;
    }

    @Override
    public CollectionCopyist<Set<V>, V> build() {
        return super.build();
    }

}
