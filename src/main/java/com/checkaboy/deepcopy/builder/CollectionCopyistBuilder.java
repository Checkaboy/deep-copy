package com.checkaboy.deepcopy.builder;

import com.checkaboy.deepcopy.builder.interf.ICollectionCopyistBuilder;
import com.checkaboy.deepcopy.cloner.FieldCloner;
import com.checkaboy.deepcopy.cloner.interf.IFieldCloner;
import com.checkaboy.deepcopy.copyist.CollectionCopyist;
import com.checkaboy.deepcopy.copyist.interf.ICollectionCopyist;
import com.checkaboy.objectutils.container.AbstractTypifiedContainer;

import java.util.Collection;

/**
 * @author Taras Shaptala
 */
public class CollectionCopyistBuilder<C extends Collection<V>, V>
        extends AbstractTypifiedContainer<V>
        implements ICollectionCopyistBuilder<V> {

    private IFieldCloner<V> fieldCloner = FieldCloner.simpleFieldCloner();

    public CollectionCopyistBuilder(Class<V> type) {
        super(type);
    }

    @Override
    public ICollectionCopyistBuilder<V> setFieldCloner(IFieldCloner<V> fieldCloner) {
        this.fieldCloner = fieldCloner;
        return this;
    }

    public ICollectionCopyist<C, V> build() {
        return new CollectionCopyist<>(fieldCloner);
    }

}
