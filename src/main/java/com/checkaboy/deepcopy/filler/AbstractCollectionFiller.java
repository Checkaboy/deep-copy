package com.checkaboy.deepcopy.filler;

import com.checkaboy.deepcopy.filler.interf.ICollectionFiller;
import com.checkaboy.deepcopy.transformer.interf.IFieldTransformer;

import java.util.Collection;

/**
 * @author Taras Shaptala
 */
public abstract class AbstractCollectionFiller<SC extends Collection<SV>, SV, TC extends Collection<TV>, TV>
        implements ICollectionFiller<SC, SV, TC, TV> {

    @Override
    public void fill(SC source, TC target) {
        if (source != null) {
            if (!target.isEmpty()) target.clear();
            if (source.isEmpty()) return;
            source.forEach(sv -> fillValue(target, sv));
        }
    }

    protected abstract void fillValue(TC target, SV sourceValue);

}
