package com.checkaboy.deepcopy.copyist;

import com.checkaboy.deepcopy.copyist.interf.IFieldCopyist;

import java.util.function.Function;

/**
 * @author Taras Shaptala
 */
public abstract class AbstractFieldCopyist<O, V>
        implements IFieldCopyist<O> {

    private final Function<O, V> extractor;

    public AbstractFieldCopyist(Function<O, V> extractor) {
        this.extractor = extractor;
    }

    @Override
    public void copy(O source, O target) {
        V sourceValue = extractor.apply(source);
        copyValue(target, sourceValue);
    }

    protected abstract void copyValue(O target, V sourceValue);

}
