package com.checkaboy.deepcopy.adapter;

import com.checkaboy.deepcopy.adapter.interf.IFieldAdapter;
import com.checkaboy.deepcopy.adapter.interf.IObjectAdapter;

import java.util.HashMap;
import java.util.function.Supplier;

/**
 * @author Taras Shaptala
 */
public class ObjectAdapter<S, T>
        extends HashMap<String, IFieldAdapter<S, T>>
        implements IObjectAdapter<S, T> {

    private final Supplier<T> constructor;

    public ObjectAdapter(Supplier<T> constructor) {
        this.constructor = constructor;
    }

    @Override
    public T clone(S source) {
        T target = constructor.get();

        for (Entry<String, IFieldAdapter<S, T>> entry : entrySet())
            entry.getValue().copy(source, target);

        return target;
    }

}
