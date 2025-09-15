package com.checkaboy.deepcopy.filler.model.abstr;

import com.checkaboy.deepcopy.context.cache.ICache;
import com.checkaboy.deepcopy.filler.model.interf.ICollectionFiller;

import java.util.Collection;

/**
 * @author Taras Shaptala
 */
public abstract class AbstractCollectionFiller<SC extends Collection<SV>, SV, TC extends Collection<TV>, TV>
        implements ICollectionFiller<SC, SV, TC, TV> {

    @Override
    public void fill(ICache cache, SC source, TC target) {
        if (source != null) {
            if (!target.isEmpty()) target.clear();
            if (source.isEmpty()) return;
            source.forEach(sv -> fillValue(cache, target, sv));
        }
    }

    protected abstract void fillValue(ICache cache, TC target, SV sourceValue);

}
