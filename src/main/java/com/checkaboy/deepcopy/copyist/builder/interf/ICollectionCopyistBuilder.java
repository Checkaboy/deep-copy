package com.checkaboy.deepcopy.copyist.builder.interf;

import com.checkaboy.deepcopy.cloner.interf.IFieldCloner;

import java.util.Collection;
import java.util.function.Predicate;

public interface ICollectionCopyistBuilder<C extends Collection<V>, V>
        extends ICopyistBuilder<C> {

    ICollectionCopyistBuilder<C, V> setFieldCloner(IFieldCloner<V> fieldCloner);

    ICollectionCopyistBuilder<C, V> setPredicate(Predicate<V> predicate);

}
