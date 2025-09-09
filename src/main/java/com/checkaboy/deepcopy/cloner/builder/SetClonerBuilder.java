package com.checkaboy.deepcopy.cloner.builder;

import com.checkaboy.deepcopy.cloner.builder.interf.ICollectionClonerBuilder;
import com.checkaboy.deepcopy.cloner.CollectionCloner;
import com.checkaboy.deepcopy.cloner.interf.ICollectionCloner;
import com.checkaboy.objectutils.container.AbstractTypifiedContainer;
import com.checkaboy.deepcopy.copyist.based.CollectionCopyist;
import com.checkaboy.deepcopy.copyist.interf.ICollectionCopyist;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

/**
 * @author Taras Shaptala
 */
public class SetClonerBuilder<V>
        extends AbstractTypifiedContainer<V>
        implements ICollectionClonerBuilder<Set<V>, V> {

    private Function<Integer, Set<V>> constructor = HashSet::new;
    private ICollectionCopyist<Set<V>, V> collectionCopyist = CollectionCopyist.primitiveCollectionCopyist();

    public SetClonerBuilder(Class<V> type) {
        super(type);
    }

    @Override
    public SetClonerBuilder<V> setConstructor(Function<Integer, Set<V>> constructor) {
        this.constructor = constructor;
        return this;
    }

    @Override
    public SetClonerBuilder<V> setCollectionCopyist(ICollectionCopyist<Set<V>, V> collectionFieldCopyist) {
        this.collectionCopyist = collectionFieldCopyist;
        return this;
    }

    public ICollectionCloner<Set<V>, V> build() {
        return new CollectionCloner<>(constructor, collectionCopyist);
    }

}
