package com.checkaboy.deepcopy.builder;

import com.checkaboy.deepcopy.cloner.interf.ICloner;
import com.checkaboy.deepcopy.copyist.CollectionCopyist;

import java.util.List;

/**
 * @author Taras Shaptala
 */
public class ListCopyistBuilder<V>
        extends CollectionCopyistBuilder<List<V>, V> {

    public ListCopyistBuilder(Class<V> type) {
        super(type);
    }

    @Override
    public ListCopyistBuilder<V> setCloner(ICloner<V> cloner) {
        super.setCloner(cloner);
        return this;
    }

    @Override
    public CollectionCopyist<List<V>, V> build() {
        return super.build();
    }

}
