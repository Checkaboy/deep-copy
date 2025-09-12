package com.checkaboy.deepcopy.context.factory;

import com.checkaboy.deepcopy.context.cache.Cache;
import com.checkaboy.deepcopy.context.cache.ICache;

/**
 * @author Taras Shaptala
 */
public class CacheFactory
        implements ICacheFactory {

    @Override
    public ICache create() {
        return new Cache();
    }

}
