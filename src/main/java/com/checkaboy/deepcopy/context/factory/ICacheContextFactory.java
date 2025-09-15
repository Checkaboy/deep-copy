package com.checkaboy.deepcopy.context.factory;

import com.checkaboy.deepcopy.context.cache.ICacheContext;

/**
 * @author Taras Shaptala
 */
@FunctionalInterface
public interface ICacheContextFactory {

    <S, T> ICacheContext<S, T> create();

}
