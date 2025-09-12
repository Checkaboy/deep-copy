package com.checkaboy.deepcopy.filler.model.abstr;

import com.checkaboy.deepcopy.cache.ICacheContext;
import com.checkaboy.deepcopy.filler.model.interf.ICollectionFiller;

import java.util.Collection;

/**
 * @author Taras Shaptala
 */
public abstract class AbstractCollectionFiller<SC extends Collection<SV>, SV, TC extends Collection<TV>, TV>
        implements ICollectionFiller<SC, SV, TC, TV> {

    @Override
    public void fill(ICacheContext cacheContext, SC source, TC target) {
        if (source != null) {
            if (!target.isEmpty()) target.clear();
            if (source.isEmpty()) return;
            source.forEach(sv -> fillValue(cacheContext, target, sv));
        }
    }

    protected abstract void fillValue(ICacheContext cacheContext, TC target, SV sourceValue);

}
