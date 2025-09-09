package com.checkaboy.deepcopy.copyist.based;

import com.checkaboy.deepcopy.copyist.AbstractObjectCopyist;
import com.checkaboy.deepcopy.copyist.interf.IFieldCopyist;
import com.checkaboy.deepcopy.copyist.interf.IObjectCopyist;

import java.util.Map;

/**
 * @author Taras Shaptala
 */
public class ObjectCopyist<O>
        extends AbstractObjectCopyist<O>
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
    protected void copyField(String fieldName, IFieldCopyist<O> fieldCopyist, O source, O target) {
        fieldCopyist.copy(source, target);
    }

}
