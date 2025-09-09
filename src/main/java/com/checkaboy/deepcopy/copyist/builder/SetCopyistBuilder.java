package com.checkaboy.deepcopy.copyist.builder;

import com.checkaboy.deepcopy.cloner.interf.IFieldCloner;
import com.checkaboy.deepcopy.copyist.interf.ICollectionCopyist;

import java.util.Set;
import java.util.function.Predicate;

/**
 * @author Taras Shaptala
 */
public class SetCopyistBuilder<V>
        extends CollectionCopyistBuilder<Set<V>, V> {

    public SetCopyistBuilder(Class<V> type) {
        super(type);
    }

    @Override
    public SetCopyistBuilder<V> setFieldCloner(IFieldCloner<V> fieldCloner) {
        super.setFieldCloner(fieldCloner);
        return this;
    }

    @Override
    public SetCopyistBuilder<V> setPredicate(Predicate<V> predicate) {
        super.setPredicate(predicate);
        return this;
    }

    @Override
    public ICollectionCopyist<Set<V>, V> build() {
        return super.build();
    }

}
