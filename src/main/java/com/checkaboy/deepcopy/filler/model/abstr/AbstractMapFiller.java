package com.checkaboy.deepcopy.filler.model.abstr;

import com.checkaboy.deepcopy.context.cache.ICopyistCache;
import com.checkaboy.deepcopy.filler.model.interf.IMapFiller;

import java.util.Map;

/**
 * @author Taras Shaptala
 */
public abstract class AbstractMapFiller<SM extends Map<SK, SV>, SK, SV, TM extends Map<TK, TV>, TK, TV>
        implements IMapFiller<SM, SK, SV, TM, TK, TV> {

    @Override
    public void fill(ICopyistCache cache, SM source, TM target) {
        if (source != null) {
            if (!target.isEmpty()) target.clear();
            if (source.isEmpty()) return;
            source.forEach((k, v) -> fillValue(cache, target, k, v));
        }
    }

    protected abstract void fillValue(ICopyistCache cache, TM target, SK key, SV sourceValue);

}
