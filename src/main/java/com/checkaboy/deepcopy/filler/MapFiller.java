package com.checkaboy.deepcopy.filler;

import com.checkaboy.deepcopy.cloner.interf.IFieldCloner;
import com.checkaboy.deepcopy.filler.interf.IMapFiller;
import com.checkaboy.deepcopy.transformer.interf.IFieldTransformer;

import java.util.Map;

/**
 * @author Taras Shaptala
 */
public class MapFiller<SM extends Map<K, SV>, SV, TM extends Map<K, TV>, TV, K>
        implements IMapFiller<SM, SV, TM, TV, K> {

    private final IFieldCloner<K> keyCloner;
    private final IFieldTransformer<SV, TV> valueTransformer;

    public MapFiller(IFieldCloner<K> keyCloner, IFieldTransformer<SV, TV> valueTransformer) {
        this.keyCloner = keyCloner;
        this.valueTransformer = valueTransformer;
    }

    @Override
    public void fill(SM source, TM target) {
        if (source != null) {
            if (!target.isEmpty()) target.clear();
            if (source.isEmpty()) return;

            source.forEach((k, v) -> target.put(keyCloner.clone(k), valueTransformer.transform(v)));
        }
    }

}
