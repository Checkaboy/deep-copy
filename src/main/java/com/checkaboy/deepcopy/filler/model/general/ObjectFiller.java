package com.checkaboy.deepcopy.filler.model.general;

import com.checkaboy.deepcopy.filler.model.abstr.AbstractObjectFiller;
import com.checkaboy.deepcopy.filler.model.interf.IFieldFiller;
import com.checkaboy.deepcopy.filler.model.interf.IObjectFiller;

import java.util.Map;

/**
 * @author Taras Shaptala
 */
public class ObjectFiller<S, T>
        extends AbstractObjectFiller<S, T>
        implements IObjectFiller<S, T> {

    public ObjectFiller() {
    }

    public ObjectFiller(int initialCapacity) {
        super(initialCapacity);
    }

    public ObjectFiller(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    public ObjectFiller(Map<? extends String, ? extends IFieldFiller<S, T>> m) {
        super(m);
    }

    @Override
    protected void fillField(String fieldName, IFieldFiller<S, T> fieldFiller, S source, T target) {
        fieldFiller.fill(source, target);
    }

}
