package com.checkaboy.deepcopy.builder.interf;

import com.checkaboy.deepcopy.cloner.interf.ICloner;

public interface ICollectionCopyistBuilder<V> {

    ICollectionCopyistBuilder<V> setCloner(ICloner<V> cloner);

}
