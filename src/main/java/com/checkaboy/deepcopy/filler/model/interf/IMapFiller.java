package com.checkaboy.deepcopy.filler.model.interf;

import java.util.Map;

/**
 * @author Taras Shaptala
 */
public interface IMapFiller<SM extends Map<SK, SV>, SK, SV, TM extends Map<TK, TV>, TK, TV>
        extends IFieldFiller<SM, TM> {
}
