package com.checkaboy.deepcopy.builder;

import com.checkaboy.deepcopy.builder.interf.ICollectionClonerBuilder;
import com.checkaboy.deepcopy.cloner.CollectionCloner;
import com.checkaboy.deepcopy.cloner.interf.ICollectionCloner;
import com.checkaboy.deepcopy.copyist.CollectionCopyist;
import com.checkaboy.deepcopy.copyist.interf.ICollectionCopyist;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

/**
 * @author Taras Shaptala
 */
public class SetClonerBuilder<V>
        implements ICollectionClonerBuilder<Set<V>, V> {

    private final Class<V> type;
    private Function<Integer, Set<V>> constructor = HashSet::new;
    private ICollectionCopyist<Set<V>, V> copyist = CollectionCopyist.primitiveCollectionCopyist();

    public SetClonerBuilder(Class<V> type) {
        this.type = type;
    }

    public SetClonerBuilder<V> setConstructor(Function<Integer, Set<V>> constructor) {
        this.constructor = constructor;
        return this;
    }

    public SetClonerBuilder<V> setCopyist(ICollectionCopyist<Set<V>, V> copyist) {
        this.copyist = copyist;
        return this;
    }

    public ICollectionCloner<Set<V>, V> build() {
        return new CollectionCloner<>(constructor, copyist);
    }

    public Class<V> getType() {
        return type;
    }

}
