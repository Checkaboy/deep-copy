package com.checkaboy.deepcopy.cloner.builder.interf;

import com.checkaboy.deepcopy.copyist.interf.IObjectCopyist;

import java.util.function.Supplier;

/**
 * @author Taras Shaptala
 */
public interface IObjectClonerBuilder<O> {

    IObjectClonerBuilder<O> setConstructor(Supplier<O> constructor);

    IObjectClonerBuilder<O> setObjectCopyist(IObjectCopyist<O> objectCopyist);

}
