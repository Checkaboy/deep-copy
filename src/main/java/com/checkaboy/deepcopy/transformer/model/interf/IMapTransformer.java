package com.checkaboy.deepcopy.transformer.model.interf;

import java.util.Map;

/**
 * @author Taras Shaptala
 */
public interface IMapTransformer<SM extends Map<SK, SV>, SK, SV, TM extends Map<TK, TV>, TK, TV>
        extends IFieldTransformer<SM, TM> {
}
