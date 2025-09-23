package com.checkaboy.deepcopy.context.factory;

import com.checkaboy.deepcopy.context.cache.ICopyistCache;

/**
 * @author Taras Shaptala
 */
@FunctionalInterface
public interface ICopyistCacheFactory {

    ICopyistCache create();

}
