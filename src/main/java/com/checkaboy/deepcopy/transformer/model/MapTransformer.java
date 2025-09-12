package com.checkaboy.deepcopy.transformer.model;

import com.checkaboy.deepcopy.cache.ICacheContext;
import com.checkaboy.deepcopy.filler.model.interf.IMapFiller;
import com.checkaboy.deepcopy.transformer.model.interf.IMapTransformer;

import java.util.Map;
import java.util.function.Function;

/**
 * @author Taras Shaptala
 */
public class MapTransformer<SM extends Map<SK, SV>, SK, SV, TM extends Map<TK, TV>, TK, TV>
        implements IMapTransformer<SM, SK, SV, TM, TK, TV> {

    private final Function<Integer, TM> constructor;
    private final IMapFiller<SM, SK, SV, TM, TK, TV> mapFiller;

    public MapTransformer(Function<Integer, TM> constructor, IMapFiller<SM, SK, SV, TM, TK, TV> mapFiller) {
        this.constructor = constructor;
        this.mapFiller = mapFiller;
    }

    @Override
    public TM transform(ICacheContext cacheContext, SM source) {
        if (source == null)
            return null;

        TM newTargetMap = constructor.apply(source.size());
        mapFiller.fill(cacheContext, source, newTargetMap);

        return newTargetMap;
    }

}
