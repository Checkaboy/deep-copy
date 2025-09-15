package com.checkaboy.deepcopy.context.factory;

import com.checkaboy.deepcopy.context.cache.ICache;

/**
 * @author Taras Shaptala
 */
@FunctionalInterface
public interface ICacheFactory {

    ICache create();

}
