package com.checkaboy.deepcopy.filler;

import com.checkaboy.deepcopy.filler.interf.IFieldFiller;
import com.checkaboy.deepcopy.filler.interf.IObjectFiller;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Taras Shaptala
 */
public class ObjectFiller<S, T>
        extends HashMap<String, IFieldFiller<S, T>>
        implements IObjectFiller<S, T> {

    public ObjectFiller(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    public ObjectFiller(int initialCapacity) {
        super(initialCapacity);
    }

    public ObjectFiller() {
    }

    public ObjectFiller(Map<? extends String, ? extends IFieldFiller<S, T>> m) {
        super(m);
    }

    @Override
    public void fill(S source, T target) {
        for (Entry<String, IFieldFiller<S, T>> entry : entrySet())
            entry.getValue().fill(source, target);
    }

}
