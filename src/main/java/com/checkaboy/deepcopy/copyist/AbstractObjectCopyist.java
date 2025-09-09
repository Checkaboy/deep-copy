package com.checkaboy.deepcopy.copyist;

import com.checkaboy.deepcopy.copyist.interf.IFieldCopyist;
import com.checkaboy.deepcopy.copyist.interf.IObjectCopyist;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Taras Shaptala
 */
public abstract class AbstractObjectCopyist<O>
        extends HashMap<String, IFieldCopyist<O>>
        implements IObjectCopyist<O> {

    public AbstractObjectCopyist() {
    }

    public AbstractObjectCopyist(int initialCapacity) {
        super(initialCapacity);
    }

    public AbstractObjectCopyist(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    public AbstractObjectCopyist(Map<? extends String, ? extends IFieldCopyist<O>> m) {
        super(m);
    }

    @Override
    public void copy(O source, O target) {
        for (Entry<String, IFieldCopyist<O>> entry : entrySet())
            copyField(entry.getKey(), entry.getValue(), source, target);
    }

    @Override
    public void fieldCopy(String fieldName, O source, O target) {
        IFieldCopyist<O> fieldCopyist = get(fieldName);
        if (fieldCopyist != null)
            copyField(fieldName, fieldCopyist, source, target);
    }

    protected abstract void copyField(String fieldName, IFieldCopyist<O> fieldCopyist, O source, O target);

}
