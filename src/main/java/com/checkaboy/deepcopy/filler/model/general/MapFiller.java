package com.checkaboy.deepcopy.filler.model.general;

import com.checkaboy.deepcopy.context.cache.ICache;
import com.checkaboy.deepcopy.filler.model.abstr.AbstractMapFiller;
import com.checkaboy.deepcopy.filler.model.interf.IMapFiller;
import com.checkaboy.deepcopy.transformer.model.interf.IFieldTransformer;

import java.util.Map;

/**
 * @author Taras Shaptala
 */
public class MapFiller<SM extends Map<SK, SV>, SK, SV, TM extends Map<TK, TV>, TK, TV>
        extends AbstractMapFiller<SM, SK, SV, TM, TK, TV>
        implements IMapFiller<SM, SK, SV, TM, TK, TV> {

    private final IFieldTransformer<SK, TK> keyTransformer;
    private final IFieldTransformer<SV, TV> valueTransformer;

    public MapFiller(IFieldTransformer<SK, TK> keyTransformer, IFieldTransformer<SV, TV> valueTransformer) {
        this.keyTransformer = keyTransformer;
        this.valueTransformer = valueTransformer;
    }

    @Override
    protected void fillValue(ICache cache, TM target, SK key, SV sourceValue) {
        target.put(keyTransformer.transform(cache, key), valueTransformer.transform(cache, sourceValue));
    }

    public static <K, SV, TV> IMapFiller<Map<K, SV>, K, SV, Map<K, TV>, K, TV> simpleMapFillerWithPrimitiveKey(IFieldTransformer<SV, TV> valueTransformer) {
        return new MapFiller<>((cacheContext, source) -> source, valueTransformer);
    }

    public static <K, V> IMapFiller<Map<K, V>, K, V, Map<K, V>, K, V> simpleMapFillerWithPrimitiveKeyAndValuePrimitive() {
        return new MapFiller<>((cacheContext, source) -> source, (cacheContext, source) -> source);
    }

}
