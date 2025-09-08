package com.checkaboy.deepcopy.copyist;

import com.checkaboy.deepcopy.copyist.interf.IFieldCopyist;
import com.checkaboy.deepcopy.copyist.interf.IObjectCopyist;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Taras Shaptala
 */
public class ObjectCopyist<O>
        extends HashMap<String, IFieldCopyist<O>>
        implements IObjectCopyist<O> {

    public ObjectCopyist() {
    }

    public ObjectCopyist(int initialCapacity) {
        super(initialCapacity);
    }

    public ObjectCopyist(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    public ObjectCopyist(Map<? extends String, ? extends IFieldCopyist<O>> m) {
        super(m);
    }

    @Override
    public void copy(O source, O target) {
        for (Entry<String, IFieldCopyist<O>> entry : entrySet())
            entry.getValue().copy(source, target);
    }

    @Override
    public void fieldCopy(String fieldName, O source, O target) {
        IFieldCopyist<O> fieldCopyist = get(fieldName);
        if (fieldCopyist != null)
            fieldCopyist.copy(source, target);
    }

}
