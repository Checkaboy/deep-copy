package com.checkaboy.deepcopy.filler.model.abstr;

import com.checkaboy.deepcopy.context.cache.ICopyistCache;
import com.checkaboy.deepcopy.filler.model.interf.IArrayFiller;

/**
 * @author Taras Shaptala
 */
public abstract class AbstractArrayFiller<S, T>
        implements IArrayFiller<S, T> {

    @Override
    public void fill(ICopyistCache cache, S[] source, T[] target) {
        if (source != null) {
            if (source.length == 0) return;
            if (target.length == 0) return;

            for (int i = 0; i < source.length; i++)
                fillValue(cache, i, target, source[i]);
        }
    }

    protected abstract void fillValue(ICopyistCache cache, int index, T[] target, S sourceValue);

}
