package com.checkaboy.deepcopy.transformer.builder.interf;

import com.checkaboy.deepcopy.filler.model.interf.IMapFiller;

import java.util.Map;
import java.util.function.Function;

/**
 * @author Taras Shaptala
 */
public interface IMapTransformerBuilder<SM extends Map<SK, SV>, SK, SV, TM extends Map<TK, TV>, TK, TV>
        extends ITransformerBuilder<SM, TM> {

    IMapTransformerBuilder<SM, SK, SV, TM, TK, TV> setMapFiller(IMapFiller<Map<SK, SV>, SK , SV, Map<TK, TV>, TK, TV> mapFiller);

    IMapTransformerBuilder<SM, SK, SV, TM, TK, TV> setConstructor(Function<Integer, Map<TK, TV>> constructor);

}
