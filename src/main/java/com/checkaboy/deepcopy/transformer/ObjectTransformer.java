package com.checkaboy.deepcopy.transformer;

import com.checkaboy.deepcopy.adapter.interf.IObjectAdapter;
import com.checkaboy.deepcopy.transformer.interf.IObjectTransformer;

import java.util.function.Supplier;

/**
 * @author Taras Shaptala
 */
public class ObjectTransformer<S, T>
        implements IObjectTransformer<S, T> {

    private final Supplier<T> constructor;
    private final IObjectAdapter<S, T> objectAdapter;

    public ObjectTransformer(Supplier<T> constructor, IObjectAdapter<S, T> objectAdapter) {
        this.constructor = constructor;
        this.objectAdapter = objectAdapter;
    }

    @Override
    public T clone(S source) {
        T target = constructor.get();
        objectAdapter.copy(source, target);
        return target;
    }

}
