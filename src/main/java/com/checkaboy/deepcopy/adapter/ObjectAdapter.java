package com.checkaboy.deepcopy.adapter;

import com.checkaboy.deepcopy.adapter.interf.IFieldAdapter;
import com.checkaboy.deepcopy.adapter.interf.IObjectAdapter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Taras Shaptala
 */
public class ObjectAdapter<S, T>
        extends HashMap<String, IFieldAdapter<S, T>>
        implements IObjectAdapter<S, T> {

    public ObjectAdapter(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    public ObjectAdapter(int initialCapacity) {
        super(initialCapacity);
    }

    public ObjectAdapter() {
    }

    public ObjectAdapter(Map<? extends String, ? extends IFieldAdapter<S, T>> m) {
        super(m);
    }

    @Override
    public void copy(S source, T target) {
        for (Entry<String, IFieldAdapter<S, T>> entry : entrySet())
            entry.getValue().copy(source, target);
    }

}
