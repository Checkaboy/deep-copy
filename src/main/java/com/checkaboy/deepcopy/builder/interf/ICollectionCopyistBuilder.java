package com.checkaboy.deepcopy.builder.interf;

import com.checkaboy.deepcopy.cloner.interf.IFieldCloner;

public interface ICollectionCopyistBuilder<V> {

    ICollectionCopyistBuilder<V> setFieldCloner(IFieldCloner<V> fieldCloner);

}
