package com.checkaboy.deepcopy.filler;

import com.checkaboy.deepcopy.filler.interf.IMapFiller;

import java.util.Map;

/**
 * @author Taras Shaptala
 */
public abstract class AbstractMapFiller<SM extends Map<K, SV>, SV, TM extends Map<K, TV>, TV, K>
        implements IMapFiller<SM, SV, TM, TV, K> {

    @Override
    public void fill(SM source, TM target) {
        if (source != null) {
            if (!target.isEmpty()) target.clear();
            if (source.isEmpty()) return;
            source.forEach((k, v) -> fillValue(target, k, v));
        }
    }

    protected abstract void fillValue(TM target, K key, SV sourceValue);

}
