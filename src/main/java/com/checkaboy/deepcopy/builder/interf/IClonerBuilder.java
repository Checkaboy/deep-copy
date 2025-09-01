package com.checkaboy.deepcopy.builder.interf;

import com.checkaboy.deepcopy.cloner.interf.IFieldCloner;

/**
 * @author Taras Shaptala
 */
public interface IClonerBuilder<O> {

    IFieldCloner<O> build();

}
