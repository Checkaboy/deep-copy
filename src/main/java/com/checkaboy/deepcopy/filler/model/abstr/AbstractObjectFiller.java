package com.checkaboy.deepcopy.filler.model.abstr;

import com.checkaboy.deepcopy.context.cache.ICache;
import com.checkaboy.deepcopy.filler.model.interf.IFieldFiller;
import com.checkaboy.deepcopy.filler.model.interf.IObjectFiller;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Taras Shaptala
 */
public abstract class AbstractObjectFiller<S, T>
        extends HashMap<String, IFieldFiller<S, T>>
        implements IObjectFiller<S, T> {

    public AbstractObjectFiller() {
    }

    public AbstractObjectFiller(int initialCapacity) {
        super(initialCapacity);
    }

    public AbstractObjectFiller(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    public AbstractObjectFiller(Map<? extends String, ? extends IFieldFiller<S, T>> m) {
        super(m);
    }

    @Override
    public void fill(ICache cache, S source, T target) {
        for (Entry<String, IFieldFiller<S, T>> entry : entrySet())
            fillField(cache, entry.getKey(), entry.getValue(), source, target);
    }

    @Override
    public void fieldFill(ICache cache, String fieldName, S source, T target) {
        IFieldFiller<S, T> fieldFiller = get(source);
        if (fieldFiller != null)
            fillField(cache, fieldName, fieldFiller, source, target);
    }

    protected abstract void fillField(ICache cache, String fieldName, IFieldFiller<S, T> fieldFiller, S source, T target);

}
