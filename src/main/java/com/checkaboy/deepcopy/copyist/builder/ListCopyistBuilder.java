package com.checkaboy.deepcopy.copyist.builder;

import com.checkaboy.deepcopy.cloner.interf.IFieldCloner;
import com.checkaboy.deepcopy.copyist.interf.ICollectionCopyist;

import java.util.List;
import java.util.function.Predicate;

/**
 * @author Taras Shaptala
 */
public class ListCopyistBuilder<V>
        extends CollectionCopyistBuilder<List<V>, V> {

    public ListCopyistBuilder(Class<V> type) {
        super(type);
    }

    @Override
    public ListCopyistBuilder<V> setFieldCloner(IFieldCloner<V> fieldCloner) {
        super.setFieldCloner(fieldCloner);
        return this;
    }

    @Override
    public ListCopyistBuilder<V> setPredicate(Predicate<V> predicate) {
        super.setPredicate(predicate);
        return this;
    }

    @Override
    public ICollectionCopyist<List<V>, V> build() {
        return super.build();
    }

}
