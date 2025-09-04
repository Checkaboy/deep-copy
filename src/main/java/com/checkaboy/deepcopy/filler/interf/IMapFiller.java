package com.checkaboy.deepcopy.filler.interf;

import java.util.Map;

/**
 * @author Taras Shaptala
 */
public interface IMapFiller<SM extends Map<K, SV>, SV, TM extends Map<K, TV>, TV, K>
        extends IFieldFiller<SM, TM> {
}
