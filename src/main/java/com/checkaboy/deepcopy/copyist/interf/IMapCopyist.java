package com.checkaboy.deepcopy.copyist.interf;

import java.util.Map;

/**
 * @author Taras Shaptala
 */
public interface IMapCopyist<M extends Map<K, V>, K, V>
        extends ICopyist<M> {
}
