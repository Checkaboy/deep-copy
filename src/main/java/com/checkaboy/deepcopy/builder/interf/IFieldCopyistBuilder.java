package com.checkaboy.deepcopy.builder.interf;

import com.checkaboy.deepcopy.cloner.interf.IFieldCloner;

/**
 * @author Taras Shaptala
 */
public interface IFieldCopyistBuilder<O> {

    IFieldCopyistBuilder<O> setFieldCloner(IFieldCloner<O> fieldCloner);

}
