package com.checkaboy.deepcopy.cloner;

import com.checkaboy.deepcopy.cloner.interf.ICollectionCloner;
import com.checkaboy.deepcopy.copyist.CollectionCopyist;
import com.checkaboy.deepcopy.copyist.interf.ICollectionCopyist;

import java.util.Collection;
import java.util.function.Function;

/**
 * @author Taras Shaptala
 */
public class CollectionCloner<C extends Collection<V>, V>
        implements ICollectionCloner<C, V> {

    private final Function<Integer, C> constructor;
    private final ICollectionCopyist<C, V> collectionCopyist;

    public CollectionCloner(Function<Integer, C> constructor, ICollectionCopyist<C, V> collectionCopyist) {
        this.constructor = constructor;
        this.collectionCopyist = collectionCopyist;
    }

    @Override
    public C clone(C source) {
        if (source == null)
            return null;

        C newTargetCollection = constructor.apply(source.size());
        collectionCopyist.copy(source, newTargetCollection);

        return newTargetCollection;
    }

    public static <C extends Collection<V>, V> ICollectionCloner<C, V> primitiveCollectionCloner(Function<Integer, C> constructor) {
        return new CollectionCloner<>(constructor, CollectionCopyist.primitiveCollectionCopyist());
    }

}
