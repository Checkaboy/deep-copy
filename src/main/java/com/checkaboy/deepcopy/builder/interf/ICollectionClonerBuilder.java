package com.checkaboy.deepcopy.builder.interf;

import com.checkaboy.deepcopy.copyist.interf.ICollectionCopyist;

import java.util.Collection;
import java.util.function.Function;

public interface ICollectionClonerBuilder<C extends Collection<V>, V> {

    ICollectionClonerBuilder<C, V> setConstructor(Function<Integer, C> constructor);

    ICollectionClonerBuilder<C, V> setCopyist(ICollectionCopyist<C, V> copyist);

}
