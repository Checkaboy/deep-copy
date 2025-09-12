package com.checkaboy.deepcopy.context.factory;

import com.checkaboy.deepcopy.context.cache.ICacheContext;

/**
 * @author Taras Shaptala
 */
public interface ICacheContextFactory {

    <S, T> ICacheContext<S, T> create();

}
