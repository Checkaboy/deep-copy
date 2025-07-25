package com.checkaboy.deepcopy.cloner;

import com.checkaboy.deepcopy.cloner.interf.ICloner;
import com.checkaboy.deepcopy.copyist.CollectionCopyist;
import com.checkaboy.deepcopy.copyist.interf.ICollectionCopyist;

import java.util.Collection;
import java.util.function.Supplier;

/**
 * @author Taras Shaptala
 */
public class CollectionCloner<C extends Collection<V>, V>
        implements ICloner<C> {

    private final Supplier<C> constructor;
    private final ICollectionCopyist<C, V> copyist;

    public CollectionCloner(Supplier<C> constructor, ICollectionCopyist<C, V> copyist) {
        this.constructor = constructor;
        this.copyist = copyist;
    }

    @Override
    public C clone(C source) {
        if (source == null)
            return null;

        C newTargetCollection = constructor.get();
        copyist.copy(source, newTargetCollection);

        return newTargetCollection;
    }

    public static <C extends Collection<V>, V> CollectionCloner<C, V> primitiveCollectionCloner(Supplier<C> constructor) {
        return new CollectionCloner<>(constructor, CollectionCopyist.primitiveCollectionCopyist());
    }

}
