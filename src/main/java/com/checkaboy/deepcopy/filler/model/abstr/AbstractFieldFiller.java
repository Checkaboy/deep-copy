package com.checkaboy.deepcopy.filler.model.abstr;

import com.checkaboy.deepcopy.cache.ICacheContext;
import com.checkaboy.deepcopy.filler.model.interf.IFieldFiller;

import java.util.function.Function;

/**
 * @author Taras Shaptala
 */
public abstract class AbstractFieldFiller<SO, TO, SV>
        implements IFieldFiller<SO, TO> {

    private final Function<SO, SV> extractor;

    public AbstractFieldFiller(Function<SO, SV> extractor) {
        this.extractor = extractor;
    }

    @Override
    public void fill(ICacheContext cacheContext, SO source, TO target) {
        SV sourceValue = extractor.apply(source);
        fillValue(cacheContext, target, sourceValue);
    }

    protected abstract void fillValue(ICacheContext cacheContext, TO target, SV sourceValue);

}
