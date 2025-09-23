package com.checkaboy.deepcopy.context.factory;

import com.checkaboy.deepcopy.context.cache.ICopyistContext;

/**
 * @author Taras Shaptala
 */
@FunctionalInterface
public interface ICopyistContextFactory {

    <S, T> ICopyistContext<S, T> create();

}
