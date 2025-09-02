package com.checkaboy.deepcopy.adapter.interf;

import java.util.Map;

/**
 * @author Taras Shaptala
 */
public interface IMapAdapter<SM extends Map<K, V>, TM extends Map<K, V>, K, V>
        extends IFieldAdapter<SM, TM> {
}
