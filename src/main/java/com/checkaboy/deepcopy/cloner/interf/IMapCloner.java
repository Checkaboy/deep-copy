package com.checkaboy.deepcopy.cloner.interf;

import java.util.Map;

/**
 * @author Taras Shaptala
 */
public interface IMapCloner<M extends Map<K, V>, K, V>
        extends IFieldCloner<M> {
}
