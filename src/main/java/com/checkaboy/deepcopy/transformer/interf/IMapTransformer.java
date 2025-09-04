package com.checkaboy.deepcopy.transformer.interf;

import java.util.Map;

/**
 * @author Taras Shaptala
 */
public interface IMapTransformer<SM extends Map<K, SV>, SV, TM extends Map<K, TV>, TV, K>
        extends IFieldTransformer<SM, TM> {
}
