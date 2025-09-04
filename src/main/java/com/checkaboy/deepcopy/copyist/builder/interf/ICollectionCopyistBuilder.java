package com.checkaboy.deepcopy.copyist.builder.interf;

import com.checkaboy.deepcopy.cloner.interf.IFieldCloner;

import java.util.Collection;

public interface ICollectionCopyistBuilder<C extends Collection<V>, V>
        extends ICopyistBuilder<C> {

    ICollectionCopyistBuilder<C, V> setFieldCloner(IFieldCloner<V> fieldCloner);

}
