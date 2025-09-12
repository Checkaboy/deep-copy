package com.checkaboy.deepcopy.filler.model.abstr;

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

    public void fill(SO source, TO target) {
        SV sourceValue = extractor.apply(source);
        fillValue(target, sourceValue);
    }

    protected abstract void fillValue(TO target, SV sourceValue);

}
