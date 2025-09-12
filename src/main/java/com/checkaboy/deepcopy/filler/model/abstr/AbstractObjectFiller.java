package com.checkaboy.deepcopy.filler.model.abstr;

import com.checkaboy.deepcopy.cache.ICacheContext;
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
    public void fill(ICacheContext cacheContext, S source, T target) {
        for (Entry<String, IFieldFiller<S, T>> entry : entrySet())
            fillField(cacheContext, entry.getKey(), entry.getValue(), source, target);
    }

    @Override
    public void fieldFill(ICacheContext cacheContext, String fieldName, S source, T target) {
        IFieldFiller<S, T> fieldFiller = get(source);
        if (fieldFiller != null)
            fillField(cacheContext, fieldName, fieldFiller, source, target);
    }

    protected abstract void fillField(ICacheContext cacheContext, String fieldName, IFieldFiller<S, T> fieldFiller, S source, T target);

}
