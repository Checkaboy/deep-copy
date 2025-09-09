package com.checkaboy.deepcopy.cloner.builder.interf;

import com.checkaboy.deepcopy.cloner.interf.IFieldCloner;

/**
 * @author Taras Shaptala
 */
public interface IFieldClonerBuilder<O>
        extends IClonerBuilder<O> {

    IFieldClonerBuilder<O> setFieldCloner(IFieldCloner<O> fieldCloner);

}
