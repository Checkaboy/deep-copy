package com.checkaboy.deepcopy.copyist.builder;

import com.checkaboy.deepcopy.cloner.FieldCloner;
import com.checkaboy.deepcopy.cloner.interf.IFieldCloner;
import com.checkaboy.deepcopy.copyist.based.CollectionCopyist;
import com.checkaboy.deepcopy.copyist.builder.interf.ICollectionCopyistBuilder;
import com.checkaboy.deepcopy.copyist.interf.ICollectionCopyist;
import com.checkaboy.deepcopy.copyist.predicative.PredicativeCollectionCopyist;
import com.checkaboy.objectutils.container.AbstractTypifiedContainer;

import java.util.Collection;
import java.util.function.Predicate;

/**
 * @author Taras Shaptala
 */
public class CollectionCopyistBuilder<C extends Collection<V>, V>
        extends AbstractTypifiedContainer<V>
        implements ICollectionCopyistBuilder<C, V> {

    private IFieldCloner<V> fieldCloner = FieldCloner.simpleFieldCloner();
    private Predicate<V> predicate;

    public CollectionCopyistBuilder(Class<V> type) {
        super(type);
    }

    @Override
    public ICollectionCopyistBuilder<C, V> setFieldCloner(IFieldCloner<V> fieldCloner) {
        this.fieldCloner = fieldCloner;
        return this;
    }

    @Override
    public ICollectionCopyistBuilder<C, V> setPredicate(Predicate<V> predicate) {
        this.predicate = predicate;
        return this;
    }

    @Override
    public ICollectionCopyist<C, V> build() {
        if (predicate == null) return new CollectionCopyist<>(fieldCloner);
        else return new PredicativeCollectionCopyist<>(fieldCloner, predicate);
    }

}
