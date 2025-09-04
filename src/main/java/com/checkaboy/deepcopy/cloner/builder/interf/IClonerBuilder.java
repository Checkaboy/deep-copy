package com.checkaboy.deepcopy.cloner.builder.interf;

import com.checkaboy.deepcopy.cloner.ICloner;

/**
 * @author Taras Shaptala
 */
public interface IClonerBuilder<O> {

    ICloner<O> build();

}
