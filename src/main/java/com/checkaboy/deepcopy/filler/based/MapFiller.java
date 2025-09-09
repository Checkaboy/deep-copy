package com.checkaboy.deepcopy.filler.based;

import com.checkaboy.deepcopy.cloner.interf.IFieldCloner;
import com.checkaboy.deepcopy.filler.AbstractMapFiller;
import com.checkaboy.deepcopy.filler.interf.IMapFiller;
import com.checkaboy.deepcopy.transformer.interf.IFieldTransformer;

import java.util.Map;

/**
 * @author Taras Shaptala
 */
public class MapFiller<SM extends Map<K, SV>, SV, TM extends Map<K, TV>, TV, K>
        extends AbstractMapFiller<SM, SV, TM, TV, K>
        implements IMapFiller<SM, SV, TM, TV, K> {

    private final IFieldCloner<K> keyCloner;
    private final IFieldTransformer<SV, TV> valueTransformer;

    public MapFiller(IFieldCloner<K> keyCloner, IFieldTransformer<SV, TV> valueTransformer) {
        this.keyCloner = keyCloner;
        this.valueTransformer = valueTransformer;
    }

    @Override
    protected void fillValue(TM target, K key, SV sourceValue) {
        target.put(keyCloner.clone(key), valueTransformer.transform(sourceValue));
    }

}
