package com.checkaboy.deepcopy.builder;

import com.checkaboy.deepcopy.builder.interf.ICollectionClonerBuilder;
import com.checkaboy.deepcopy.cloner.CollectionCloner;
import com.checkaboy.deepcopy.cloner.interf.ICollectionCloner;
import com.checkaboy.deepcopy.copyist.CollectionCopyist;
import com.checkaboy.deepcopy.copyist.interf.ICollectionCopyist;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * @author Taras Shaptala
 */
public class ListClonerBuilder<V>
        implements ICollectionClonerBuilder<List<V>, V> {

    private final Class<V> type;
    private Function<Integer, List<V>> constructor = ArrayList::new;
    private ICollectionCopyist<List<V>, V> copyist = CollectionCopyist.primitiveCollectionCopyist();

    public ListClonerBuilder(Class<V> type) {
        this.type = type;
    }

    public ListClonerBuilder<V> setConstructor(Function<Integer, List<V>> constructor) {
        this.constructor = constructor;
        return this;
    }

    public ListClonerBuilder<V> setCopyist(ICollectionCopyist<List<V>, V> copyist) {
        this.copyist = copyist;
        return this;
    }

    public ICollectionCloner<List<V>, V> build() {
        return new CollectionCloner<>(constructor, copyist);
    }

    public Class<V> getType() {
        return type;
    }

}
