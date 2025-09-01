package com.checkaboy.deepcopy.builder.interf;

import com.checkaboy.deepcopy.cloner.interf.IFieldCloner;

/**
 * @author Taras Shaptala
 */
public interface IFieldClonerBuilder<O> {

    IFieldClonerBuilder<O> setFieldCloner(IFieldCloner<O> fieldCloner);

}
