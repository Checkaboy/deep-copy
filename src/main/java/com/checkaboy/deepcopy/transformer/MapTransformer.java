package com.checkaboy.deepcopy.transformer;

import com.checkaboy.deepcopy.filler.interf.IMapFiller;
import com.checkaboy.deepcopy.transformer.interf.IMapTransformer;

import java.util.Map;
import java.util.function.Function;

/**
 * @author Taras Shaptala
 */
public class MapTransformer<SM extends Map<K, SV>, SV, TM extends Map<K, TV>, TV, K>
        implements IMapTransformer<SM, SV, TM, TV, K> {

    private final Function<Integer, TM> constructor;
    private final IMapFiller<SM, SV, TM, TV, K> mapFiller;

    public MapTransformer(Function<Integer, TM> constructor, IMapFiller<SM, SV, TM, TV, K> mapFiller) {
        this.constructor = constructor;
        this.mapFiller = mapFiller;
    }

    @Override
    public TM transform(SM source) {
        if (source == null)
            return null;

        TM newTargetMap = constructor.apply(source.size());
        mapFiller.fill(source, newTargetMap);

        return newTargetMap;
    }

}
