package com.checkaboy.deepcopy.copyist.builder;

import com.checkaboy.deepcopy.cloner.FieldCloner;
import com.checkaboy.deepcopy.cloner.interf.IFieldCloner;
import com.checkaboy.deepcopy.copyist.CollectionCopyist;
import com.checkaboy.deepcopy.copyist.builder.interf.ICollectionCopyistBuilder;
import com.checkaboy.deepcopy.copyist.interf.ICollectionCopyist;
import com.checkaboy.objectutils.container.AbstractTypifiedContainer;

import java.util.Collection;

/**
 * @author Taras Shaptala
 */
public class CollectionCopyistBuilder<C extends Collection<V>, V>
        extends AbstractTypifiedContainer<V>
        implements ICollectionCopyistBuilder<C, V> {

    private IFieldCloner<V> fieldCloner = FieldCloner.simpleFieldCloner();

    public CollectionCopyistBuilder(Class<V> type) {
        super(type);
    }

    @Override
    public ICollectionCopyistBuilder<C, V> setFieldCloner(IFieldCloner<V> fieldCloner) {
        this.fieldCloner = fieldCloner;
        return this;
    }

    @Override
    public ICollectionCopyist<C, V> build() {
        return new CollectionCopyist<>(fieldCloner);
    }

}
