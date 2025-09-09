package com.checkaboy.deepcopy.transformer.builder.interf;

import com.checkaboy.deepcopy.filler.interf.IMapFiller;

import java.util.Map;
import java.util.function.Function;

/**
 * @author Taras Shaptala
 */
public interface IMapTransformerBuilder<SM extends Map<K, SV>, SV, TM extends Map<K, TV>, TV, K>
        extends ITransformerBuilder<SM, TM> {

    IMapTransformerBuilder<SM, SV, TM, TV, K> setMapFiller(IMapFiller<Map<K, SV>, SV, Map<K, TV>, TV, K> mapFiller);

    IMapTransformerBuilder<SM, SV, TM, TV, K> setConstructor(Function<Integer, Map<K, TV>> constructor);

}
