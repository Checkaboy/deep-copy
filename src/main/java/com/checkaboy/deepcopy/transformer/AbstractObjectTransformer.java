package com.checkaboy.deepcopy.transformer;

import com.checkaboy.deepcopy.transformer.interf.IObjectTransformer;

/**
 * @author Taras Shaptala
 */
public abstract class AbstractObjectTransformer<S, T>
        implements IObjectTransformer<S, T> {

    @Override
    public T transform(S source) {
        if (source == null)
            return null;

        T target = create();
        fill(source, target);
        return target;
    }

    protected abstract T create();

    protected abstract void fill(S source, T target);


}
